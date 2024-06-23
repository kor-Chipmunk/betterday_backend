package com.mashup.port;

public interface FcmPort {
    int pushMessage(String title, String token, String to);
}
