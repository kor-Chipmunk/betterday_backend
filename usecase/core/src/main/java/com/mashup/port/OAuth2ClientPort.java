package com.mashup.port;

import lombok.Data;

public interface OAuth2ClientPort {
    Response getToken(Request request);

    Profile getProfile(String accessToken);

    @Data
    class Request {
        private final String providerType;
        private final String grantCode;
    }

    @Data
    class Response {
        private final String accessToken;
        private final String refreshToken;
    }

    @Data
    class Profile {
        private final String id;
        private final String email;
    }
}
