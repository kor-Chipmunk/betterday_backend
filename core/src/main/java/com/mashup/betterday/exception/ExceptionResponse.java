package com.mashup.betterday.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExceptionResponse {
    private final int code;
    private final String message;

    public static ExceptionResponse from(BusinessException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        return new ExceptionResponse(
                errorCode.getCode(),
                errorCode.getMessage()
        );
    }
}
