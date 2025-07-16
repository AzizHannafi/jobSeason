package com.injob.back.model;

import com.injob.back.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "job_apply")
@Data
public class JobApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_offer_id", nullable = false)
    private JobOffer jobOffer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum status;

    @Column(nullable = false)
    private String email;

    @Column(name = "date_envoi", nullable = false)
    private LocalDateTime dateEnvoi;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String coverLettre;

    @Column(nullable = false)
    private String yearsofProfessionnalExperience;

    @Column(nullable = false)
    private String educationDegree;

    @OneToMany(mappedBy = "jobApply", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interview> interviews;
}
