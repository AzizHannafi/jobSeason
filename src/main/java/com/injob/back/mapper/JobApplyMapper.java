package com.injob.back.mapper;

import com.injob.back.dto.JobApplyDto;
import com.injob.back.model.JobApply;
import com.injob.back.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.Collections;

@Mapper(componentModel = "spring", imports = {Collections.class})
public interface JobApplyMapper {

    @Mapping(source = "dateEnvoi", target = "dateEnvoi", qualifiedByName = "formatLocalDateTime")
    @Mapping(source = "jobOffer.description", target = "jobDescription")
    @Mapping(source = "coverLettre", target = "coverLettre")
    @Mapping(source = "yearsofProfessionnalExperience", target = "yearsofProfessionnalExperience")
    @Mapping(source = "educationDegree", target = "educationDegree")
    JobApplyDto jobApplyToDto(JobApply jobApply);
    @Mapping(source = "dateEnvoi", target = "dateEnvoi", qualifiedByName = "formatStringDate")
    JobApply toJobApply(JobApplyDto jobApplyDto);


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
