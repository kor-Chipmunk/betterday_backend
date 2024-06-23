package com.mashup.feelring;

import com.mashup.feelring.user.model.User;
import lombok.Data;

public interface AuthKakaoLoginUsecase {
    LoginResponse login(Request request);

    @Data
    class Request {
        private final String code;
        private final String redirectUri;
    }

    @Data
    class LoginResponse {
        private final User user;
        private final String accessToken;
        private final String refreshToken;
    }
}
