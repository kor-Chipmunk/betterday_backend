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
import com.mashup.betterday.model.auth.OAuth2AppleRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Tag(name = "OAuth2 인증", description = "OAuth2 인증 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth/oauth2")
public class OAuth2Controller {

    private final OAuth2TokenUsecase oAuth2TokenUsecase;
    private final OAuth2LoginUsecase oAuth2LoginUsecase;
    private final OAuth2AuthorizeUsecase oAuth2AuthorizeUsecase;

    private final UserCreateUsecase userCreateUsecase;

    @Operation(summary = "OAuth2 로그인", description = "프로바이더 유형과 인가 코드로 토큰을 OAuth2 서버로부터 전달받아 로그인합니다.")
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

    @Operation(summary = "OAuth2 로그인", description = "GET 메소드와 동일합니다. 애플에서 호출하는 POST 메소드입니다.")
    @PostMapping("/{providerType}")
    public ResponseEntity<AuthDto> loginPost(
            @PathVariable String providerType,
            OAuth2AppleRequest request // application/x-www-form-urlencoded;charset=UTF-8 인식 위해, @RequestBody 제거
    ) {
        OAuth2TokenUsecase.TokenResponse tokenResponse = oAuth2TokenUsecase.getToken(
                new TokenRequest(
                        providerType,
                        request.getCode()
                )
        );

        // 웹에서 호출 시 Token 정보 URL 노출 방지로 Redirect 안함
        return this.loginFromToken(providerType, tokenResponse.getAccessToken());
    }

    @Operation(summary = "OAuth2 로그인", description = "프로바이더 유형과 액세스 토큰으로 로그인합니다.")
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

    @Operation(summary = "OAuth2 인가", description = "프로바이더 유형으로 인가 URL을 반환합니다.")
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
