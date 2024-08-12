package com.mashup.betterday.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.ZIP;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtService {

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.access-token.expiration}")
    private Long accessTokenExpirationSeconds;

    @Value("${jwt.refresh-token.expiration}")
    private Long refreshTokenExpirationSeconds;

    private final SecretKey accessTokenSecret;
    private final SecretKey refreshTokenSecret;

    public JwtService(
            @Value("#{environment['jwt.access-token.secret-key']}")
            String accessTokenSecretKey,
            @Value("#{environment['jwt.refresh-token.secret-key']}")
            String refreshTokenSecretKey
    ) {
        accessTokenSecretKey = Encoders.BASE64.encode(accessTokenSecretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenSecret = Keys.hmacShaKeyFor(accessTokenSecretKey.getBytes(StandardCharsets.UTF_8));

        refreshTokenSecretKey = Encoders.BASE64.encode(refreshTokenSecretKey.getBytes(StandardCharsets.UTF_8));
        this.refreshTokenSecret = Keys.hmacShaKeyFor(refreshTokenSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Long id, String email, Date baseDate) {
        final long expirationMillis = baseDate.getTime() + Duration.ofSeconds(accessTokenExpirationSeconds).toMillis();
        final Date expiration = new Date(expirationMillis);

        return Jwts.builder()
                .header()
                    .type(Header.JWT_TYPE)
                .and()
                .issuer(issuer)
                .expiration(expiration)
                .subject(email)
                .claim("id", id)
                .compressWith(ZIP.GZIP)
                .signWith(accessTokenSecret)
                .compact();
    }

    public String generateRefreshToken(Date baseDate) {
        final long expirationMillis = baseDate.getTime() + Duration.ofSeconds(refreshTokenExpirationSeconds).toMillis();
        final Date expiration = new Date(expirationMillis);

        return Jwts.builder()
                .header()
                .type(Header.JWT_TYPE)
                .and()
                .issuer(issuer)
                .expiration(expiration)
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
}
