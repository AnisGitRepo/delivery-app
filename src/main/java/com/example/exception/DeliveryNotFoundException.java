package com.example.exception;


public class DeliveryNotFoundException extends RuntimeException{

    public DeliveryNotFoundException(String message) {
        super(message);
    }

}
