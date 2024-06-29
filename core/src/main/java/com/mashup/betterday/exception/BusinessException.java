package com.mashup.betterday.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public static BusinessException from(ErrorCode errorCode) {
        return new BusinessException(errorCode);
    }
}
