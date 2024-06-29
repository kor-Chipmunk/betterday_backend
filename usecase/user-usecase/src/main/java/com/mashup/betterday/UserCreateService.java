package com.mashup.betterday;

import com.mashup.betterday.user.model.Account;
import com.mashup.betterday.user.model.Profile;
import com.mashup.betterday.user.model.Provider;
import com.mashup.betterday.user.model.ProviderType;
import com.mashup.betterday.user.model.Role;
import com.mashup.betterday.user.model.User;
import com.mashup.betterday.user.model.UserId;
import com.mashup.port.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCreateService implements UserCreateUsecase {

    private final UserPort userPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(Request request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.signUp(
                new Account(request.getEmail(), encodedPassword),
                Role.USER,
                new Profile(request.getNickname(), request.getImage()),
                new Provider(
                        ProviderType.valueOf(request.getProviderType()),
                        request.getProviderId()
                ),
                () -> new UserId(0L)
        );
        return userPort.save(user);
    }
}