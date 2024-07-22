package com.mashup.betterday.fcm.exception;

public class FCMTokenValidationException extends RuntimeException {
    public FCMTokenValidationException() {
        super();
    }

    public FCMTokenValidationException(String message) {
        super(message);
    }

    public FCMTokenValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
