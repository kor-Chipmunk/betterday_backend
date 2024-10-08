package com.mashup.betterday;

import lombok.Data;

public interface OAuth2TokenUsecase {
    TokenResponse getToken(TokenRequest tokenRequest);

    ProfileResponse getProfile(ProfileRequest profileRequest);

    @Data
    class TokenRequest {
        private final String providerType;
        private final String grantCode;
    }

    @Data
    class TokenResponse {
        private final String accessToken;
        private final String refreshToken;
    }

    @Data
    class ProfileRequest {
        private final String providerType;
        private final String accessToken;
    }

    @Data
    class ProfileResponse {
        private final String id;
        private final String email;
    }
}
