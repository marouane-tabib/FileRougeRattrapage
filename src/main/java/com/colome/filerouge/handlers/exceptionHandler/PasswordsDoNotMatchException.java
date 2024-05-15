package com.colome.filerouge.handlers.exceptionHandler;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
