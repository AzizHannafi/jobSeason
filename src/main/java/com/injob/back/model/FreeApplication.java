package com.injob.back.model;

import com.injob.back.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "free_application")
@Data
public class FreeApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String lettre;

    @Column(name = "experience")
    private String experience;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum status;
}
