package com.mashup.betterday.diary.exception;

public class ContentValidationException extends RuntimeException {
    public ContentValidationException() {
        super();
    }

    public ContentValidationException(String message) {
        super(message);
    }

    public ContentValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
