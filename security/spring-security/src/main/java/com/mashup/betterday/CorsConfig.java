package com.mashup.betterday;

import com.mashup.betterday.properties.CorsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@Configuration
public class CorsConfig {

    private final CorsProperties corsProperties;

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = getConfiguration();
        UrlBasedCorsConfigurationSource source = getConfigurationSource(configuration);
        return new CorsFilter(source);
    }

    private CorsConfiguration getConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 허용할 Origin 설정
        configuration.setAllowedOrigins(corsProperties.getAllowedOrigins());

        // 허용할 HTTP 메소드 설정
        configuration.setAllowedMethods(corsProperties.getAllowedMethods());

        // 자격 증명 허용 (쿠키 등)
        configuration.setAllowCredentials(corsProperties.getAllowedCredentials());

        return configuration;
    }

    private UrlBasedCorsConfigurationSource getConfigurationSource(CorsConfiguration configuration) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
