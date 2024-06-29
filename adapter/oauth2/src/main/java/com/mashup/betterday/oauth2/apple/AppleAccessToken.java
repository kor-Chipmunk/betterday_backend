package com.mashup.betterday.oauth2.apple;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class AppleAccessToken {
    private final String access_token;
    private final String expires_in;
    private final String id_token;
    private final String scope;
    private final String token_type;
    private final String refresh_token;
    private final String refresh_token_expires_in;
}
