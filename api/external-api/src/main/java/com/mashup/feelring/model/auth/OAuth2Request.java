package com.mashup.feelring.model.auth;

import lombok.Data;

@Data
public class OAuth2Request {
    private String providerType;
    private String proverId;
    private String fcmToken;
}
