package com.mashup.betterday;

public interface PrivacyEncryptor {
    String encrypt(String raw) throws Exception;
    String decrypt(String encrypted) throws Exception;
}
