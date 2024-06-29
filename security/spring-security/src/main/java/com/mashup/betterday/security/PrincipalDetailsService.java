package com.mashup.betterday.security;

import com.mashup.betterday.UserReadUsecase;
import com.mashup.betterday.user.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PrincipalDetailsService implements UserDetailsService {

    private final UserReadUsecase userReadUsecase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userReadUsecase.read(username);
        return new PrincipalDetails(user);
    }
}
