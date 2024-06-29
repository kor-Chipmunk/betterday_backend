package com.mashup.betterday;

import com.mashup.betterday.user.model.User;
import com.mashup.port.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUpdateService implements UserUpdateUsecase {

    private final UserPort userPort;

    @Override
    public User update(Request request) {
        return null;
    }
}
