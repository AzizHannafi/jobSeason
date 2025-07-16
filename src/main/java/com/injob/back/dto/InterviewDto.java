package com.injob.back.dto;

import com.injob.back.enums.InterviewStatusEnum;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class InterviewDto {

    private Long id;

    private String interviewDate;

    private String email;

    private String description;

    private InterviewStatusEnum interviewStatus = InterviewStatusEnum.PLANNED;

    private Long jobApplyId;

    private String jobDescription;
}
