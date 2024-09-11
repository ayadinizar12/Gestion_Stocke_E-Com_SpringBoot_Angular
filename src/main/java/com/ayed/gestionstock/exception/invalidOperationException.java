package com.ayed.gestionstock.exception;

import lombok.Getter;

public class invalidOperationException extends RuntimeException {

    @Getter
    private errorCodes errorCode;

    public invalidOperationException(String message) {
        super(message);
    }

    public invalidOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public invalidOperationException(String message, Throwable cause, errorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public invalidOperationException(String message, errorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}