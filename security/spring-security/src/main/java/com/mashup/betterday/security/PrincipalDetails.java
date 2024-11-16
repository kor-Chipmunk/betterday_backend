package com.mashup.betterday.security;

import com.mashup.betterday.user.model.User;
import java.util.Collection;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
@Getter
public class PrincipalDetails implements UserDetails {

    private final User user;

    @Override
    public String getUsername() {
        return user.getAccount().getEmail();
    }

    @Override
    public String getPassword() {
        return user.getAccount().getPassword();
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }
}
