package com.injob.back.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserSkillsDto {
    @JsonProperty("skill_name")
    private String skillName;
    @JsonProperty("skill_level")
    private String skillLevel;
    @JsonProperty("description")
    private String description;

}
