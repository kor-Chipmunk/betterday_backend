package com.mashup.feelring.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GlobalResponse<T> {
    private final int code;
    private final String message;
    private final T data;

    public static <T> GlobalResponse from(ResponseCode responseCode, T data) {
        return new GlobalResponse(
                responseCode.getCode(),
                responseCode.getMessage(),
                data
        );
    }
}
