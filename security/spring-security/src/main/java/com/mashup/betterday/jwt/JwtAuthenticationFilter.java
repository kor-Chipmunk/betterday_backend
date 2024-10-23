package com.mashup.betterday.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer ";

    private final AuthenticationManager jwtAuthenticationManager;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws IOException, ServletException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!isValidTokenPrefix(authorizationHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = extractToken(authorizationHeader);
        final Authentication jwtToken = new JwtAuthenticationToken(token);

        try {
            final Authentication authentication = jwtAuthenticationManager.authenticate(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            request.setAttribute("exception", e);
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private boolean isValidTokenPrefix(String header) {
        return header != null && header.startsWith(TOKEN_PREFIX);
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring(TOKEN_PREFIX.length());
    }
}
