package com.injob.back.model;

import com.injob.back.enums.InterviewStatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "interview")
@Data
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "interview_datetime", nullable = false)
    private LocalDateTime interviewDateTime;

    @Column(nullable = false)
    private String email;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "interview_status", nullable = false)
    private InterviewStatusEnum interviewStatus = InterviewStatusEnum.PLANNED;

    @ManyToOne
    @JoinColumn(name = "job_apply_id", unique = true)
    private JobApply jobApply;
}
