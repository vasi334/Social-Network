package com.example.lab4try.domain.validators;


import com.example.lab4try.domain.User;

public class ValidatorUser implements Validator<User> {
    @Override
    public void validate(User entity) throws ValidationException {
        if(entity.getFirstName().length() < 1){
            throw new ValidationException("Firstname cannot be null.");
        }
        else if(entity.getLastName().length() <  1){
            throw new ValidationException("Lastname cannot be null.");
        }
        else if(entity.getAge() < 0 || entity.getAge() > 99){
            throw new ValidationException("Please enter an age number between 0 and 99.");
        }
    }
}
