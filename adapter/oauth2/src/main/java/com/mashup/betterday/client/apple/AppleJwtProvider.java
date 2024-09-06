package com.mashup.betterday.client.apple;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppleJwtProvider {

    public String createClientSecret(
            String teamId,
            String clientId,
            String keyId,
            PrivateKey privateKey
    ) {
        Instant now = Instant.now();
        Date issueDate = Date.from(now);
        Date expirationDate = Date.from(now.plusSeconds(86400)); // 1 day expiration

        return Jwts.builder()
                .header()
                .add("kid", keyId)
                .add("alg", "RS256")
                .and()
                .issuer(teamId)
                .issuedAt(issueDate)
                .expiration(expirationDate)
                .audience()
                .add("https://appleid.apple.com")
                .and()
                .subject(clientId)
                .signWith(privateKey)
                .compact();
    }

    public Claims parseClaims(String idToken, PublicKey publicKey) {
        try {
            return Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(idToken)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("Expired token");
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid token");
        }
    }

}
