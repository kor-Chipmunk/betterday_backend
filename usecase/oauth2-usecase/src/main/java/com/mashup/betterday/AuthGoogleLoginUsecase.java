package com.mashup.betterday;

import com.mashup.betterday.user.model.User;
import lombok.Data;

public interface AuthGoogleLoginUsecase {
    LoginResponse login(Request request);

    @Data
    class Request {
        private final String providerType;
        private final String token;
    }

    @Data
    class LoginResponse {
        private final User user;
        private final String accessToken;
        private final String refreshToken;
    }
}
