package com.mashup.betterday.client;

import com.mashup.betterday.user.model.ProviderType;
import lombok.Data;

public interface OAuth2Client {
    TokenResponse getToken(TokenRequest tokenRequest);

    ProfileResponse getProfile(ProfileRequest profileRequest);

    boolean supports(ProviderType providerType);

    String getAuthorizeURL();

    @Data
    class TokenRequest {
        private final String grantCode;
    }

    @Data
    class ProfileRequest {
        private final String accessToken;
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
