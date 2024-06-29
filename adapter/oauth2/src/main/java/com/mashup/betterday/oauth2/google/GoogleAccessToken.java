package com.mashup.betterday.oauth2.google;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class GoogleAccessToken {
    private final String access_token;
    private final String expires_in;
    private final String id_token;
    private final String scope;
    private final String token_type;
    private final String refresh_token;
}
