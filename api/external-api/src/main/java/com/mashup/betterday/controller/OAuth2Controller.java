package com.mashup.betterday.controller;

import com.mashup.betterday.OAuth2LoginUsecase;
import com.mashup.betterday.OAuth2TokenUsecase;
import com.mashup.betterday.UserCreateUsecase;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.model.auth.AuthDto;
import com.mashup.betterday.user.model.ProviderType;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth/oauth2")
public class OAuth2Controller {

    private final OAuth2TokenUsecase oAuth2TokenUsecase;
    private final OAuth2LoginUsecase oAuth2LoginUsecase;

    private final UserCreateUsecase userCreateUsecase;

    @GetMapping("/google")
    ResponseEntity<AuthDto> login(
            @RequestParam String code,
            @RequestParam String scope,
            @RequestParam String authuser,
            @RequestParam String prompt
    ) {
        OAuth2TokenUsecase.TokenResponse tokenResponse = oAuth2TokenUsecase.getToken(
                new OAuth2TokenUsecase.Request(
                        ProviderType.GOOGLE.name(),
                        code
                )
        );

        // TODO: App용 위한 아래쪽 로직 따로 분리하기
        OAuth2TokenUsecase.ProfileResponse profileResponse = oAuth2TokenUsecase.getProfile(
                tokenResponse.getAccessToken());

        try {
            OAuth2LoginUsecase.LoginResponse response = oAuth2LoginUsecase.login(
                    new OAuth2LoginUsecase.Request(
                            ProviderType.GOOGLE.name(),
                            profileResponse.getEmail()
                    )
            );

            return ResponseEntity.ok(
                    AuthDto.from(
                            response.getUser(),
                            response.getAccessToken(),
                            response.getRefreshToken()
                    )
            );
        } catch (BusinessException ex) {
            if (ex.getErrorCode().equals(ErrorCode.USER_NOT_FOUND)) {
                userCreateUsecase.create(
                        new UserCreateUsecase.Request(
                                profileResponse.getEmail(),
                                UUID.randomUUID().toString(),
                                ProviderType.GOOGLE.name(),
                                profileResponse.getId()
                        )
                );

                OAuth2LoginUsecase.LoginResponse response = oAuth2LoginUsecase.login(
                        new OAuth2LoginUsecase.Request(
                                ProviderType.GOOGLE.name(),
                                profileResponse.getId()
                        )
                );

                return ResponseEntity.ok(
                        AuthDto.from(
                                response.getUser(),
                                response.getAccessToken(),
                                response.getRefreshToken()
                        )
                );
            } else {
                throw ex;
            }
        }
    }
}
