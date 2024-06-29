package com.mashup.betterday.user.model.exception;

public class UserValidationException extends RuntimeException {
    public UserValidationException() {
        super();
    }

    public UserValidationException(String message) {
        super(message);
    }

    public UserValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
