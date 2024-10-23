package com.mashup.betterday;

import com.mashup.betterday.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private static final String[] POST_WHITE_LIST = {
            "/api/v1/users/**",
            "/api/v1/auth/**",
            "/api/v1/alarms"
    };
    private static final String[] GET_WHITE_LIST = {
            "/", "/h2-console", "/h2-console/**", "/favicon.ico", // default
            "/v3/**", "/swagger-ui/**", "/swagger-resources/**", // swagger
            "/api/v1/auth/oauth2/**" // oauth2
    };

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final HandlerExceptionResolver resolver;

    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                             @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.resolver = resolver;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable);

        http.headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin));

        http.sessionManagement(config ->
                config.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.exceptionHandling(config ->
                config.authenticationEntryPoint((request, response, authException) ->
                        resolver.resolveException(request, response, null,
                                (Exception) request.getAttribute("exception"))
                ));

        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(HttpMethod.POST, POST_WHITE_LIST).permitAll()
                        .requestMatchers(GET_WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
        );

        http.addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}
