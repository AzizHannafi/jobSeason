package com.injob.back.dto;

import com.injob.back.enums.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FreeApplicationDto {


    private Long id;
    private String description;

    private String email;

    private String lettre;

    private String experience;

    private Integer yearsOfExperience;

    private String phoneNumber;
    private StatusEnum status=StatusEnum.PENDING;
}
