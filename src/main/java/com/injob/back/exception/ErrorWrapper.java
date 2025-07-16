package com.injob.back.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;

import java.time.ZonedDateTime;


@Builder
@AllArgsConstructor
@Data
public class ErrorWrapper {

    private Boolean success;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private final ZonedDateTime date = ZonedDateTime.now();
    private String message;
    private String code;


}
