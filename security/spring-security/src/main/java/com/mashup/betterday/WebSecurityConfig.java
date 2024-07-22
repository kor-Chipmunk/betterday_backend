package com.mashup.betterday;

import com.mashup.betterday.jwt.JwtAuthenticationFilter;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private static final String[] POST_WHITE_LIST = {
            "/api/v1/users/**",
            "/api/v1/auth/**",
            "/api/v1/alarms"
    };
    private static final String[] GET_WHITE_LIST = {
            "/", "/h2-console", "/h2-console/**", "/favicon.ico", // default
            "/v3/**", "/swagger-ui/**", "/swagger-resources/**", // swagger
            "/login/oauth2/**" // oauth2
    };

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable);

        http.headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin));

        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        http.sessionManagement(config ->
                config.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.exceptionHandling(Customizer.withDefaults());

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

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://43.203.91.150:10000/", "http://localhost:10000", "http://api.betterday.one", "https://api.betterday.one"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
