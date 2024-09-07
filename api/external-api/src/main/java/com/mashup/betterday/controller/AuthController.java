package com.mashup.betterday.controller;

import com.mashup.betterday.AuthLoginUsecase;
import com.mashup.betterday.OAuth2LoginUsecase;
import com.mashup.betterday.RefreshTokenUsecase;
import com.mashup.betterday.UserCreateUsecase;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.model.auth.AuthDto;
import com.mashup.betterday.model.auth.AuthRequest;
import com.mashup.betterday.model.auth.OAuth2Request;
import com.mashup.betterday.model.auth.RefreshTokenRequest;
import com.mashup.betterday.model.auth.TokenDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthLoginUsecase authLoginUsecase;
    private final OAuth2LoginUsecase oAuth2LoginUsecase;
    private final RefreshTokenUsecase refreshTokenUsecase;

    private final UserCreateUsecase userCreateUsecase;

    @PostMapping("/login")
    ResponseEntity<AuthDto> login(
            @RequestBody AuthRequest request
    ) {
        AuthLoginUsecase.LoginResponse loginResponse = authLoginUsecase.login(
                new AuthLoginUsecase.Request(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        return ResponseEntity.ok(
                AuthDto.from(
                        loginResponse.getUser(),
                        loginResponse.getAccessToken(),
                        loginResponse.getRefreshToken()
                )
        );
    }

    @PostMapping("/login/oauth2")
    ResponseEntity<AuthDto> loginOauth2(
            @RequestBody OAuth2Request request
    ) {
        try {
            OAuth2LoginUsecase.LoginResponse response = oAuth2LoginUsecase.login(
                    new OAuth2LoginUsecase.Request(
                            request.getProviderType(),
                            request.getProviderId()
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
                                request.getEmail(),
                                UUID.randomUUID().toString(),
                                request.getProviderType(),
                                request.getProviderId()
                        )
                );

                OAuth2LoginUsecase.LoginResponse response = oAuth2LoginUsecase.login(
                        new OAuth2LoginUsecase.Request(
                                request.getProviderType(),
                                request.getProviderId()
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

    @PostMapping("/refresh")
    ResponseEntity<TokenDto> refresh(@RequestBody RefreshTokenRequest request) {

        RefreshTokenUsecase.TokenResponse response = refreshTokenUsecase.refresh(
                new RefreshTokenUsecase.Request(request.getRefreshToken())
        );

        return ResponseEntity.ok(
                new TokenDto(
                        response.getAccessToken(),
                        request.getRefreshToken()
                )
        );
    }
}
