package com.mashup.port;

import com.mashup.betterday.user.model.ProviderType;
import lombok.Data;

public interface OAuth2ClientPort {
    TokenResponse getToken(TokenRequest tokenRequest);

    ProfileResponse getProfile(ProfileRequest profileRequest);

    AuthorizeResponse getAuthorizeURL(AuthorizeRequest request);

    @Data
    class TokenRequest {
        private final ProviderType providerType;
        private final String grantCode;
    }

    @Data
    class TokenResponse {
        private final String accessToken;
        private final String refreshToken;
    }

    @Data
    class ProfileRequest {
        private final ProviderType providerType;
        private final String accessToken;
    }

    @Data
    class ProfileResponse {
        private final String id;
        private final String email;
    }

    @Data
    class AuthorizeRequest {
        private final ProviderType providerType;
    }

    @Data
    class AuthorizeResponse {
        private final String authorizeURL;
    }
}
