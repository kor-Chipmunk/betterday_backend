package com.mashup.betterday;

import lombok.Data;

public interface RefreshTokenUsecase {
    TokenResponse refresh(Request request);

    @Data
    class Request {
        private final String refreshToken;
    }

    @Data
    class TokenResponse {
        private final String accessToken;
    }
}
