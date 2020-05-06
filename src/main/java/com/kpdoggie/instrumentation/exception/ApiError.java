package com.kpdoggie.instrumentation.exception;


import lombok.Builder;
import org.springframework.http.HttpStatus;


@Builder
public class ApiError {

    // To be displayed by frontend
    public String title;

    public HttpStatus status;
    public String detail;

    // Unique identifier of the trace
    public String instance;

}
