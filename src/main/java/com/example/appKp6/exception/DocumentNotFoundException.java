package com.example.appKp6.exception;

public class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException(Long id){
        super("Could not found the document with id "+ id);
    }
}
