package com.mashup.betterday.user.exception;

public class AdminValidationException extends RuntimeException {
    public AdminValidationException() {
        super();
    }

    public AdminValidationException(String message) {
        super(message);
    }

    public AdminValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
