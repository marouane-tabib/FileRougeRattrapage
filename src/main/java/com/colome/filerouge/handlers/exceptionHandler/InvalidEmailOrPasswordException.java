package com.colome.filerouge.handlers.exceptionHandler;

public class InvalidEmailOrPasswordException extends RuntimeException{
    public InvalidEmailOrPasswordException(String message){
        super(message);
    }
}
