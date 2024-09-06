package com.mashup.betterday.client.apple.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class JWTSet implements Serializable {
    @Serial
    private static final long serialVersionUID = -3088076051210716511L;

    private List<JWTSet.Keys> keys;

    public JWTSet.Keys getMatchedKey(String kid, String alg) {
        return keys.stream()
                .filter(key -> key.getKid().equals(kid) && key.getAlg().equals(alg))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found matched key"));
    }

    @Data
    public static class Keys implements Serializable {
        @Serial
        private static final long serialVersionUID = -1269093660755451176L;

        private String kty;
        private String kid;
        private String alg;
        private String n; // Base64 Encoded Modulus
        private String e; // Base64 Encoded Exponent

        public byte[] getDecodedNBytes() {
            return java.util.Base64.getUrlDecoder().decode(n);
        }

        public byte[] getDecodedEBytes() {
            return java.util.Base64.getUrlDecoder().decode(e);
        }
    }

}
