package com.mashup.feelring.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    OK(200, "성공입니다.")
    ;

    private final int code;
    private final String message;
}
