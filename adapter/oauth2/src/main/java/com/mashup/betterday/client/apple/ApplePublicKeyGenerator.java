package com.mashup.betterday.client.apple;

import com.mashup.betterday.client.apple.model.JWTSet;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplePublicKeyGenerator {

    private static final int POSITIVE_SIGNUM = 1;

    public PublicKey generatePublicKey(
            Map<String, String> tokenHeaders,
            JWTSet applePublicKeys
    ) {
        JWTSet.Keys publicKey = applePublicKeys.getMatchedKey(
                tokenHeaders.get("kid"),
                tokenHeaders.get("alg")
        );

        return getPublicKey(publicKey);
    }

    private PublicKey getPublicKey(JWTSet.Keys publicKey) {
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(
                new BigInteger(POSITIVE_SIGNUM, publicKey.getDecodedNBytes()),
                new BigInteger(POSITIVE_SIGNUM, publicKey.getDecodedEBytes())
        );

        try {
            KeyFactory keyFactory = KeyFactory.getInstance(publicKey.getKty());
            return keyFactory.generatePublic(publicKeySpec);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to generate public key");
        }
    }
}
