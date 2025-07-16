package com.injob.back.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_skills")
@Data
public class UserSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "skill_name",nullable = false)
    private String skillName;
    @Column(name = "skill_level",nullable = false)
    private String skillLevel;
    private String description;
    @Column(name = "user_email",nullable = false)
    private String userEmail;
}
