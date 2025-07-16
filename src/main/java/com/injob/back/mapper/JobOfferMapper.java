package com.injob.back.mapper;

import com.injob.back.dto.JobOfferDto;
import com.injob.back.model.JobOffer;
import com.injob.back.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;

@Mapper(componentModel = "spring", imports = {Collections.class})
public interface JobOfferMapper {


    @Mapping(source = "dateDebut", target = "dateDebut", qualifiedByName = "formatLocalDateTime")
    @Mapping(source = "dateCloture", target = "dateCloture", qualifiedByName = "formatLocalDateTime")
    @Mapping(source = "datePublication", target = "datePublication", qualifiedByName = "formatLocalDateTime")
    JobOfferDto jobOfferToDto(JobOffer offer);

    @Mapping(source = "dateDebut", target = "dateDebut", qualifiedByName = "formatStringDate")
    @Mapping(source = "dateCloture", target = "dateCloture", qualifiedByName = "formatStringDate")
    @Mapping(source = "datePublication", target = "datePublication", qualifiedByName = "formatStringDate")
    JobOffer toJobOffer(JobOfferDto offer);


    @Named("formatLocalDateTime")
    static String formatLocalDateTime(LocalDateTime dateTime) {
        if (dateTime != null) {
            return DateUtils.convertDateTimeToStringDate(dateTime);
        }
        return null;
    }

    @Named("formatStringDate")
    static LocalDateTime formatStringDate(String date) {
        if (date != null) {
            return DateUtils.convertStringDateToDateTime(date);
        }
        return null;
    }
}
