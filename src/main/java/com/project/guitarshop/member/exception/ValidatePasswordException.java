package com.project.guitarshop.member.exception;

public class ValidatePasswordException extends RuntimeException {

    public ValidatePasswordException() {
    }

    public ValidatePasswordException(String message) {
        super(message);
    }

    public ValidatePasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatePasswordException(Throwable cause) {
        super(cause);
    }
}
