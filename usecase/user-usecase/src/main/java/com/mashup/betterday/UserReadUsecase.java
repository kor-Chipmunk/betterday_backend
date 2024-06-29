package com.mashup.betterday;

import com.mashup.betterday.user.model.User;

public interface UserReadUsecase {
    User read(Long id);
    User read(String email);
}
