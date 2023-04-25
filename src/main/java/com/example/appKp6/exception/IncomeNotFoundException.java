package com.example.appKp6.exception;

public class IncomeNotFoundException extends RuntimeException {
    public IncomeNotFoundException(Long id){
        super("Could not found the income with id "+ id);
    }
}
