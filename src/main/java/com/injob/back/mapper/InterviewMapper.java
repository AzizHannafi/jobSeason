package com.injob.back.mapper;

import com.injob.back.dto.InterviewDto;
import com.injob.back.dto.PostInterviewDto;
import com.injob.back.model.Interview;
import com.injob.back.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.Collections;

@Mapper(componentModel = "spring", imports = {Collections.class})
public interface InterviewMapper {
    @Mapping(source = "interviewDateTime", target = "interviewDate", qualifiedByName = "formatLocalDateTime")
    @Mapping(source = "jobApply.jobOffer.description", target = "jobDescription")
    @Mapping(source = "jobApply.email", target = "email")
    @Mapping(source = "jobApply.id", target = "jobApplyId")
    InterviewDto toInterviewDto(Interview interview);
    @Mapping(source = "interviewDate", target = "interviewDateTime", qualifiedByName = "formatStringDate")
    @Mapping(source = "email", target = "jobApply.email")
    @Mapping(source = "jobApplyId", target = "jobApply.id")
    Interview toInterview(InterviewDto interviewDto);

    @Mapping(source = "interviewDateTime", target = "interviewDate", qualifiedByName = "formatLocalDateTime")
    PostInterviewDto toPostInterviewDto(Interview interview);
    @Mapping(source = "interviewDate", target = "interviewDateTime", qualifiedByName = "formatStringDate")
    Interview postToInterview(PostInterviewDto interviewDto);



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
