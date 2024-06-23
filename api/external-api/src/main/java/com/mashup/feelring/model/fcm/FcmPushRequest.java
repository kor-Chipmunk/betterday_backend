package com.mashup.feelring.model.fcm;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmPushRequest {
    private String token;
    private String title;
    private String body;

    @Builder
    public FcmPushRequest(
            String token,
            String title,
            String body
    ) {
        this.token = token;
        this.title = title;
        this.body = body;
    }
}
