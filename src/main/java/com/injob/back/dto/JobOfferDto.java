package com.injob.back.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.injob.back.enums.JobOfferTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class JobOfferDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("date_publication")
    private String datePublication;

    @JsonProperty("date_cloture")
    private String dateCloture;

    @JsonProperty("date_debut")
    private String  dateDebut;

    @JsonProperty("type")
    private JobOfferTypeEnum offerType;
}
