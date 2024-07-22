package com.mashup.betterday.user.model;

import com.mashup.betterday.common.link.model.ImageLink;
import com.mashup.betterday.user.model.exception.UserValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Profile {

    private String nickname;
    private ImageLink image;

    public Profile(String nickname, ImageLink image) {
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
