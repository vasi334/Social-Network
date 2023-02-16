package com.example.lab4try.domain.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}
