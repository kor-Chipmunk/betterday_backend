package com.mashup.betterday.controller;

import com.mashup.betterday.*;
import com.mashup.betterday.model.auth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthLoginUsecase authLoginUsecase;

    private final AuthGoogleLoginUsecase authGoogleLoginUsecase;

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
        authGoogleLoginUsecase.login(new AuthGoogleLoginUsecase.Request(
                request.getProviderType(),
                request.getToken()
        ));
        return ResponseEntity.ok(null);
    }

    @PostMapping("/refresh")
    ResponseEntity<Object> refresh(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(null);
    }
}
