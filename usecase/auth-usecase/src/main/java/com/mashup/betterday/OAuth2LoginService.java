package com.mashup.betterday;

import com.mashup.betterday.jwt.JwtService;
import com.mashup.betterday.user.model.Provider;
import com.mashup.betterday.user.model.ProviderType;
import com.mashup.betterday.user.model.User;
import com.mashup.port.OAuth2Port;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OAuth2LoginService implements OAuth2LoginUsecase {

    private final OAuth2Port oAuth2Port;

    private final JwtService jwtService;

    @Override
    public LoginResponse login(Request request) {
        User user = oAuth2Port.findByProvider(
                new Provider(
                        ProviderType.from(request.getProviderType()),
                        request.getProviderId()
                )
        );

        String accessToken = jwtService.generateAccessToken(
                user.getId().getValue(),
                user.getAccount().getEmail(),
                new Date()
        );

        String refreshToken = jwtService.generateRefreshToken(new Date());

        return new LoginResponse(
                user,
                accessToken,
                refreshToken
        );
    }
}
