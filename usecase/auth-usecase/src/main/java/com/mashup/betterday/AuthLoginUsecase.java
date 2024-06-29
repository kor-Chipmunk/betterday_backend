package com.mashup.betterday;

import com.mashup.betterday.user.model.User;
import lombok.Data;

public interface AuthLoginUsecase {
    LoginResponse login(Request request);

    @Data
    class Request {
        private final String email;
        private final String password;
    }

    @Data
    class LoginResponse {
        private final User user;
        private final String accessToken;
        private final String refreshToken;
    }
}
