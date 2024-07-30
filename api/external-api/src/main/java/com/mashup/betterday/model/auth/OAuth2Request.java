package com.mashup.betterday.model.auth;

import lombok.Data;

@Data
public class OAuth2Request {
    private String email;
    private String nickname;
    private String providerType;
    private String providerId;
}
