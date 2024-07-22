package com.mashup.port;

import com.mashup.betterday.user.model.User;
import com.mashup.betterday.user.model.UserId;

public interface UserPort {
    User save(User user);
    User findById(UserId id);
    User findByEmail(String email);
}
