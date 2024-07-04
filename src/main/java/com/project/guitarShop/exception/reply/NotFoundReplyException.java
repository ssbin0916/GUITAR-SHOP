package com.project.guitarShop.exception.reply;

public class NotFoundReplyException extends RuntimeException {

    public NotFoundReplyException() {
    }

    public NotFoundReplyException(String message) {
        super(message);
    }

    public NotFoundReplyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundReplyException(Throwable cause) {
        super(cause);
    }
}

