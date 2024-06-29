package com.mashup.betterday.jwt;

import com.mashup.betterday.security.PrincipalDetails;
import com.mashup.betterday.security.UserDetailsToken;
import com.mashup.betterday.exception.BusinessException;
import com.mashup.betterday.exception.ErrorCode;
import com.mashup.betterday.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService detailsService;
    private final JwtService jwtService;

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isInstance(JwtAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String token = (String) authentication.getPrincipal();

        final long id = jwtService.getPayload(token, "id", Long.class);
        final String userId = jwtService.getSubject(token);

        PrincipalDetails principal = (PrincipalDetails) detailsService.loadUserByUsername(userId);
        User user = principal.getUser();

        if (user.getId().getValue() != id) {
            throw BusinessException.from(ErrorCode.USER_NOT_FOUND);
        }

        return new UserDetailsToken(
                principal,
                principal.getPassword()
        );
    }
}
