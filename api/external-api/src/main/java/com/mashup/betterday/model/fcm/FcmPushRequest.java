package com.mashup.betterday.model.fcm;

import lombok.Data;

@Data
public class FcmPushRequest {
    private String token;
    private String title;
    private String body;
}
