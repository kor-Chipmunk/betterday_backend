package com.mashup.betterday;

import com.mashup.betterday.common.link.model.Link;
import com.mashup.betterday.user.model.ProviderType;
import com.mashup.port.OAuth2ClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OAuth2AuthorizeService implements OAuth2AuthorizeUsecase {

    private final OAuth2ClientPort oAuth2Port;

    @Override
    public AuthorizeResponse getAuthorizeURL(AuthorizeRequest request) {
        OAuth2ClientPort.AuthorizeResponse response = oAuth2Port.getAuthorizeURL(
                new OAuth2ClientPort.AuthorizeRequest(
                        ProviderType.from(request.getProviderType())
                )
        );
        return new AuthorizeResponse(
                new Link(response.getAuthorizeURL())
        );
    }
}
