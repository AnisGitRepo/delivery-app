package com.carrefour.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ChangePasswordException.class)
    public ResponseEntity handleChangePasswordException(ChangePasswordException ex) {
        return new ResponseEntity<>(body(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UnauthorizedOperationException.class)
    public ResponseEntity handleUnauthorizedOperationException(UnauthorizedOperationException ex) {
        return new ResponseEntity<>(body(ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(body(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ClientNotFoundException.class)
    public ResponseEntity handleClientNotFoundException(ClientNotFoundException ex) {
        return new ResponseEntity<>(body(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DeliveryNotFoundException.class)
    public ResponseEntity handleDeliveryNotFoundException(DeliveryNotFoundException ex) {
        return new ResponseEntity<>(body(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    private Map<String, String> body(String message) {
        Map<String, String> map = new HashMap<>();
        map.put("message", message);
        return map;
    }
}
