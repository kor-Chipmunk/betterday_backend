package com.mashup.betterday.model.user;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String nickname;
    private String image;
}
