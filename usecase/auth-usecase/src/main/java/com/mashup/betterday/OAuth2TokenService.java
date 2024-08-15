package com.mashup.betterday;

import com.mashup.betterday.user.model.ProviderType;
import com.mashup.port.OAuth2ClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OAuth2TokenService implements OAuth2TokenUsecase {

    private final OAuth2ClientPort oAuth2Port;

    @Override
    public TokenResponse getToken(TokenRequest request) {
        OAuth2ClientPort.TokenResponse tokenResponse = oAuth2Port.getToken(
                new OAuth2ClientPort.TokenRequest(
                        ProviderType.from(request.getProviderType()),
                        request.getGrantCode())
        );

        return new TokenResponse(tokenResponse.getAccessToken(), tokenResponse.getRefreshToken());
    }

    @Override
    public ProfileResponse getProfile(ProfileRequest request) {
        OAuth2ClientPort.ProfileResponse profileResponse = oAuth2Port.getProfile(
                new OAuth2ClientPort.ProfileRequest(
                        ProviderType.from(request.getProviderType()),
                        request.getAccessToken()
                )
        );

        return new ProfileResponse(profileResponse.getId(), profileResponse.getEmail());
    }
}
