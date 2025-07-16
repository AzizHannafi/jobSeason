package com.injob.back.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.injob.back.enums.StatusEnum;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JobApplyDto {

    private Long id;
    private StatusEnum status= StatusEnum.PENDING;
    private String email;
    private String jobDescription;
    private String coverLettre;
    private String yearsofProfessionnalExperience;
    private String educationDegree;

    @JsonProperty(value = "date_envoi",defaultValue = "2023-12-25T18:14:34.017Z")
    private String dateEnvoi;
}
