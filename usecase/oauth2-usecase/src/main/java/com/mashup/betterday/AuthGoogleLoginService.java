package com.mashup.betterday;

import com.mashup.betterday.common.link.model.ImageLink;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.jwt.JwtService;
import com.mashup.betterday.model.OAuthToken;
import com.mashup.betterday.model.OAuthUser;
import com.mashup.betterday.oauth2.google.GoogleClient;
import com.mashup.betterday.user.model.Account;
import com.mashup.betterday.user.model.Profile;
import com.mashup.betterday.user.model.Provider;
import com.mashup.betterday.user.model.ProviderType;
import com.mashup.betterday.user.model.Role;
import com.mashup.betterday.user.model.User;
import com.mashup.betterday.user.model.UserId;
import com.mashup.port.UserPort;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthGoogleLoginService implements AuthGoogleLoginUsecase {

    private final UserPort userPort;

    private final JwtService jwtService;

    private final GoogleClient googleClient;

    @Override
    public LoginResponse login(Request request) {
        OAuthToken oAuthToken = googleClient.requestAccessToken(request.getToken());
        OAuthUser oAuthUser = googleClient.requestUserInfo(oAuthToken);

        User user;
        try {
            user = userPort.findByEmail(oAuthUser.getEmail());
        } catch (BusinessException exception) {
            user = User.signUp(
                    UserId.empty(),
                    new Account(oAuthUser.getEmail(), ""),
                    Role.USER,
                    new Profile("Nickname", new ImageLink(oAuthUser.getProfileImage())),
                    new Provider(
                            ProviderType.valueOf(request.getProviderType()),
                            oAuthUser.getUserId()
                    )
            );
        }

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
