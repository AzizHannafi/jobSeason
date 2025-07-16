package com.injob.back.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class PostInterviewDto {
    private String interviewDate;
    private String description;

}
