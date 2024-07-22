package com.mashup.betterday.common.link.exception;

public class LinkValidationException extends RuntimeException {
    public LinkValidationException() {
        super();
    }

    public LinkValidationException(String message) {
        super(message);
    }

    public LinkValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
