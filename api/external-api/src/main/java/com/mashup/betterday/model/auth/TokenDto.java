package com.mashup.betterday.model.auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class TokenDto {
    private final String accessToken;
    private final String refreshToken;
}
