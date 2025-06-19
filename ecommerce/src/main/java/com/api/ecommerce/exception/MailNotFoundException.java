package com.api.ecommerce.exception;

public class MailNotFoundException extends RuntimeException {
    public MailNotFoundException(String message) {
        super(message);
    }
}

