package com.mashup.betterday.fcm.model;

import com.mashup.betterday.fcm.exception.FCMTokenValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class FCMToken {

    private String value;

    public FCMToken(String value) {
        validateToken(value);

        this.value = value;
    }

    private void validateToken(String value) {
        if (value.isBlank()) {
            throw new FCMTokenValidationException("토큰은 필수 입력값입니다.");
        }
    }

}
