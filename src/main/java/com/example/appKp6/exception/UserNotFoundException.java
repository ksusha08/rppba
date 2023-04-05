package com.example.appKp6.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id){
        super("Could not found the user with id "+ id);
    }
}
