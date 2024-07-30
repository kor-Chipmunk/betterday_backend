package com.mashup.betterday.model.user;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String email;
    private String password;

    private String providerType;
    private String providerId;
}
