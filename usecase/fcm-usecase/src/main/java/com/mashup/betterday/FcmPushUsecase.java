package com.mashup.betterday;

import lombok.Data;

public interface FcmPushUsecase {
    Response pushMessage(Request request);

    @Data
    class Request {
        private final String token;
        private final String title;
        private final String body;
    }

    @Data
    class Response {
        private final int result;
    }
}
