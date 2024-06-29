package com.mashup.betterday;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger log = LoggerFactory.getLogger(OAuth2LoginSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String token = (String) authentication.getPrincipal();
        log.info("Token : " + token);
        response.addHeader("Authorization", "Bearer " + token);
    }
}
