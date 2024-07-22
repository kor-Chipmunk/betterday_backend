package com.mashup.betterday.model.fcm;

import com.mashup.betterday.fcm.model.FCM;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class FcmDto {
    private final String token;

    public static FcmDto from(FCM fcm) {
        return new FcmDto(fcm.getToken().getValue());
    }
}
