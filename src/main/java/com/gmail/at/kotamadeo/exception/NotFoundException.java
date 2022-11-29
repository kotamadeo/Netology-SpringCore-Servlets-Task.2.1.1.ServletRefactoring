package com.gmail.at.kotamadeo.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7585013941600899246L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
