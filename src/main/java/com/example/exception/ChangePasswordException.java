package com.example.exception;

public class ChangePasswordException extends RuntimeException{

    public ChangePasswordException(String message) {
        super(message);
    }

}
