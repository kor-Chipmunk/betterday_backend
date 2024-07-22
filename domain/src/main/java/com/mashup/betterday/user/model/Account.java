package com.mashup.betterday.user.model;

import com.mashup.betterday.user.model.exception.UserValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Account {

    private String email;
    private String password;

    public Account(String email, String password) {
        validate(email, password);

        this.email = email;
        this.password = password;
    }

    private void validate(String email, String password) {
        if (email.isBlank() || password.isBlank()) {
            throw new UserValidationException("이메일과 패스워드는 필수 입력값입니다.");
        }
    }
}
