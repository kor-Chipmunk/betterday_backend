package com.mashup.betterday;

import com.mashup.betterday.common.link.model.Link;
import lombok.Data;

public interface OAuth2AuthorizeUsecase {
    AuthorizeResponse getAuthorizeURL(AuthorizeRequest request);

    @Data
    class AuthorizeRequest {
        private final String providerType;
    }

    @Data
    class AuthorizeResponse {
        private final Link authorizeLink;
    }

}
