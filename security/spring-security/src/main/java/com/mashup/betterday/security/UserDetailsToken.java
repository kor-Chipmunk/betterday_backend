package com.mashup.betterday.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserDetailsToken extends UsernamePasswordAuthenticationToken {
    public UserDetailsToken(PrincipalDetails userDetails, String password) {
        super(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public Object getCredentials() {
        return super.getCredentials();
    }

    @Override
    public Object getPrincipal() {
        return super.getPrincipal();
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        super.setAuthenticated(isAuthenticated);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
