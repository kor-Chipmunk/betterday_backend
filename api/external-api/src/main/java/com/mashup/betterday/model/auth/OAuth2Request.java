package com.mashup.betterday.model.auth;

import lombok.Data;

@Data
public class OAuth2Request {
    private String providerType;
    private String token;
}
