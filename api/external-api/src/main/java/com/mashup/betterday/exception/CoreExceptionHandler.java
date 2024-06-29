package com.mashup.betterday.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CoreExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("UNKNOWN ERROR", ex);

        BusinessException unknownException = BusinessException.from(ErrorCode.UNKNOWN);
        ExceptionResponse response = ExceptionResponse.from(unknownException);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getCode()));
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleBusinessException(Exception ex, WebRequest request) {
        log.error("BUSINESS EXCEPTION", ex);

        BusinessException businessException = (BusinessException) ex;
        ExceptionResponse response = ExceptionResponse.from(businessException);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getCode()));
    }
}
