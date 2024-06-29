package com.mashup.betterday.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class OAuthToken {

    private final String oAuthAccessToken;
    private final String oAuthRefreshToken;
    private final String oAuthTokenType;

    public static OAuthToken of(String oAuthAccessToken, String oAuthRefreshToken, String oAuthTokenType) {
        return OAuthToken.builder()
                .oAuthAccessToken(oAuthAccessToken)
                .oAuthRefreshToken(oAuthRefreshToken)
                .oAuthTokenType(oAuthTokenType)
                .build();
    }
}
