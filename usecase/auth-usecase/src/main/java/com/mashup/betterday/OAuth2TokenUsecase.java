package com.mashup.betterday;

import lombok.Data;

public interface OAuth2TokenUsecase {
    TokenResponse getToken(Request request);

    ProfileResponse getProfile(String accessToken);

    @Data
    class Request {
        private final String providerType;
        private final String grantCode;
    }

    @Data
    class TokenResponse {
        private final String accessToken;
        private final String refreshToken;
    }

    @Data
    class ProfileResponse {
        private final String id;
        private final String email;
    }
}
