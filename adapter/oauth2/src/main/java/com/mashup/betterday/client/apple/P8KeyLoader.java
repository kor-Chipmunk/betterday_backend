package com.mashup.betterday.client.apple;

import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class P8KeyLoader {

    public PrivateKey loadPrivateKey(Resource resource) {
        try (
                InputStream inputStream = resource.getInputStream();
                StringReader reader = new StringReader(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8))
        ) {
            var pemParser = new PEMParser(reader);
            var pemObject = pemParser.readObject();
            var converter = new JcaPEMKeyConverter();
            return converter.getPrivateKey(
                    PrivateKeyInfo.getInstance(pemObject)
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid key", e);
        }
    }

}
