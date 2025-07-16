package com.injob.back.model;

import com.injob.back.enums.JobOfferTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "job_offer")
@Data
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "date_publication", nullable = false)
    private LocalDateTime datePublication;

    @Column(name = "date_cloture")
    private LocalDateTime dateCloture;

    @Column(name = "date_debut", nullable = false)
    private LocalDateTime  dateDebut;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private JobOfferTypeEnum offerType;

    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL)
    private List<JobApply> jobApplies;

    @Column(name = "companyEmail", nullable = false)
    private String companyEmail;
}
