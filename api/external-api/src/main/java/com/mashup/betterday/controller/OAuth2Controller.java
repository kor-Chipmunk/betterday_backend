package com.mashup.betterday.controller;

import com.mashup.betterday.OAuth2AuthorizeUsecase;
import com.mashup.betterday.OAuth2LoginUsecase;
import com.mashup.betterday.OAuth2TokenUsecase;
import com.mashup.betterday.OAuth2TokenUsecase.ProfileRequest;
import com.mashup.betterday.OAuth2TokenUsecase.TokenRequest;
import com.mashup.betterday.UserCreateUsecase;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.model.auth.AuthDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth/oauth2")
public class OAuth2Controller {

    private final OAuth2TokenUsecase oAuth2TokenUsecase;
    private final OAuth2LoginUsecase oAuth2LoginUsecase;
    private final OAuth2AuthorizeUsecase oAuth2AuthorizeUsecase;

    private final UserCreateUsecase userCreateUsecase;

    @GetMapping("/{providerType}")
    public ResponseEntity<AuthDto> login(
            @PathVariable String providerType,
            @RequestParam String code,
            @RequestParam(required = false) String scope,
            @RequestParam(required = false) String authuser,
            @RequestParam(required = false) String prompt
    ) {
        OAuth2TokenUsecase.TokenResponse tokenResponse = oAuth2TokenUsecase.getToken(
                new TokenRequest(
                        providerType,
                        code
                )
        );

        // 웹에서 호출 시 Token 정보 URL 노출 방지로 Redirect 안함
        return this.loginFromToken(providerType, tokenResponse.getAccessToken());
    }

    @GetMapping("/{providerType}/token")
    public ResponseEntity<AuthDto> loginFromToken(
            @PathVariable String providerType,
            @RequestParam String accessToken
    ) {
        log.info("accessToken: {}", accessToken);

        OAuth2TokenUsecase.ProfileResponse profileResponse = oAuth2TokenUsecase.getProfile(
                new ProfileRequest(
                        providerType,
                        accessToken
                )
        );
        try {
            OAuth2LoginUsecase.LoginResponse response = oAuth2LoginUsecase.login(
                    new OAuth2LoginUsecase.Request(
                            providerType,
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
        } catch (BusinessException ex) {
            if (ex.getErrorCode().equals(ErrorCode.USER_NOT_FOUND)) {
                userCreateUsecase.create(
                        new UserCreateUsecase.Request(
                                profileResponse.getEmail(),
                                UUID.randomUUID().toString(),
                                providerType,
                                profileResponse.getId()
                        )
                );

                OAuth2LoginUsecase.LoginResponse response = oAuth2LoginUsecase.login(
                        new OAuth2LoginUsecase.Request(
                                providerType,
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

    @GetMapping("/{providerType}/authorize")
    public RedirectView authorize(@PathVariable String providerType) {
        OAuth2AuthorizeUsecase.AuthorizeResponse response = oAuth2AuthorizeUsecase.getAuthorizeURL(
                new OAuth2AuthorizeUsecase.AuthorizeRequest(
                        providerType
                )
        );

        return new RedirectView(response.getAuthorizeLink().getLink());
    }
}
