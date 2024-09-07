package com.mashup.betterday.model.auth;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
