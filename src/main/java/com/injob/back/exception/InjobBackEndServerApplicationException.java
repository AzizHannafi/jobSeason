package com.injob.back.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public class InjobBackEndServerApplicationException  extends RuntimeException {

    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus;
}
