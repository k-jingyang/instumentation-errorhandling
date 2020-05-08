package com.kpdoggie.instrumentation.exception;

import com.kpdoggie.instrumentation.KmoConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = {IllegalArgumentException.class})

    protected ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException e,
            WebRequest webRequest) {

        log.info("TEST");
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .title("Lorem")
                .detail("Lorem ipsum")
                .instance(webRequest.getHeader(KmoConstants.TRACING_ID_HEADER))
                .build();

        return ResponseEntity.unprocessableEntity().body(apiError);
    }
}
