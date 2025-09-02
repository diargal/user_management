package com.nisum.user_management.domain.exception;

public class ExistEmailException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExistEmailException(String message) {
        super(message);
    }
}
