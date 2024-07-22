package com.mashup.port;

public interface FcmPushPort {
    int pushMessage(String title, String token, String to);
}
