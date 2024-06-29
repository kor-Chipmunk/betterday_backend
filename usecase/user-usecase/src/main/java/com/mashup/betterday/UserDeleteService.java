package com.mashup.betterday;

import com.mashup.betterday.user.model.User;
import com.mashup.port.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDeleteService implements UserDeleteUsecase {

    private final UserPort userPort;

    @Override
    public User delete(Request request) {
        return null;
    }
}
