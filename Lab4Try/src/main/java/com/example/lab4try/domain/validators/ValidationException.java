package com.example.lab4try.domain.validators;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }
}
