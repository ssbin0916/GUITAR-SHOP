package com.project.guitarshop.order.exception;

public class OrderCancelException extends RuntimeException {

    public OrderCancelException() {
        super();
    }

    public OrderCancelException(String message) {
        super(message);
    }

    public OrderCancelException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderCancelException(Throwable cause) {
        super(cause);
    }
}
