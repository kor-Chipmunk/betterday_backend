package com.mashup.betterday.user.model;

import com.mashup.betterday.user.model.exception.UserValidationException;
import lombok.Data;

@Data
public class Profile {

    private String nickname;
    private String image;

    public Profile(String nickname, String image) {
        validateNickname(nickname);

        this.nickname = nickname;
        this.image = image;
    }

    private void validateNickname(String nickname) {
        if (nickname.isBlank()) {
            throw new UserValidationException("닉네임은 필수 입력값입니다.");
        }
    }

}
