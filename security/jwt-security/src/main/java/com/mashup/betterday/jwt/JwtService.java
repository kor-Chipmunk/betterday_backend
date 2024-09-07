package com.mashup.betterday.jwt;

import com.mashup.betterday.jwt.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.ZIP;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtService {

    private final JwtProperties jwtProperties;

    private final SecretKey accessTokenSecret;
    private final SecretKey refreshTokenSecret;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;

        this.accessTokenSecret = Keys.hmacShaKeyFor(
                jwtProperties.getAccessToken()
                        .getSecretKey()
                        .getBytes(StandardCharsets.UTF_8)
        );

        this.refreshTokenSecret = Keys.hmacShaKeyFor(
                jwtProperties.getRefreshToken()
                        .getSecretKey()
                        .getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateAccessToken(Long id, String email, Date baseDate) {
        final long accessTokenExpirationSeconds = jwtProperties.getAccessToken().getExpiration();
        final long expirationMillis = baseDate.getTime() + Duration.ofSeconds(accessTokenExpirationSeconds).toMillis();
        final Date expiration = new Date(expirationMillis);

        return Jwts.builder()
                .header()
                .type(Header.JWT_TYPE)
                .and()
                .issuer(jwtProperties.getIssuer())
                .expiration(expiration)
                .subject(email)
                .claim("id", id)
                .compressWith(ZIP.GZIP)
                .signWith(accessTokenSecret)
                .compact();
    }

    public String generateRefreshToken(Long id, String email, Date baseDate) {
        final long refreshTokenExpirationSeconds = jwtProperties.getRefreshToken().getExpiration();
        final long expirationMillis = baseDate.getTime() + Duration.ofSeconds(refreshTokenExpirationSeconds).toMillis();
        final Date expiration = new Date(expirationMillis);

        return Jwts.builder()
                .header()
                .type(Header.JWT_TYPE)
                .and()
                .issuer(jwtProperties.getIssuer())
                .expiration(expiration)
                .subject(email)
                .claim("id", id)
                .compressWith(ZIP.GZIP)
                .signWith(refreshTokenSecret)
                .compact();
    }

    public <T> T getPayload(String token, String key, Class<T> clazz) {
        final Claims jwtPayload = getPayload(token);
        return jwtPayload.get(key, clazz);
    }

    public String getSubject(String token) {
        final Claims jwtPayload = getPayload(token);
        return jwtPayload.getSubject();
    }

    private Claims getPayload(String token) {
        final Jws<Claims> claims = parseClaims(token);
        return claims.getPayload();
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(accessTokenSecret)
                .build()
                .parseSignedClaims(token);
    }

    public boolean verifyRefreshToken(String token) {
        return Jwts.parser()
                .verifyWith(refreshTokenSecret)
                .build()
                .isSigned(token);
    }

    public <T> T getRefreshTokenPayload(String token, String key, Class<T> clazz) {
        final Claims jwtPayload = getRefreshTokenPayload(token);
        return jwtPayload.get(key, clazz);
    }

    public String getRefreshTokenSubject(String token) {
        final Claims jwtPayload = getRefreshTokenPayload(token);
        return jwtPayload.getSubject();
    }

    private Claims getRefreshTokenPayload(String token) {
        final Jws<Claims> claims = parseRefreshTokenClaims(token);
        return claims.getPayload();
    }

    private Jws<Claims> parseRefreshTokenClaims(String token) {
        return Jwts.parser()
                .verifyWith(refreshTokenSecret)
                .build()
                .parseSignedClaims(token);
    }

}
