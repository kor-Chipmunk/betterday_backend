package com.mashup.feelring.model.user;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String email;
    private String password;

    private String nickname;
    private String image;

    private String providerType;
    private String providerId;
}
