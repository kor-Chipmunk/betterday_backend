package com.mashup.betterday;

import com.mashup.betterday.user.model.User;
import lombok.Data;

public interface UserCreateUsecase {
    User create(Request request);

    @Data
    class Request {
        private final String email;
        private final String password;
        private final String providerType;
        private final String providerId;
    }
}
