package com.mashup.betterday;

import com.mashup.betterday.fcm.model.FCM;
import com.mashup.betterday.user.model.User;
import lombok.Data;

public interface FcmRegisterUsecase {
    FCM register(Request request);

    @Data
    class Request {
        private final User user;
        private final String token;
    }
}
