package com.example.lab4try.service;

import com.example.lab4try.domain.User;
import com.example.lab4try.domain.validators.Validator;
import com.example.lab4try.domain.validators.ValidatorUser;
import com.example.lab4try.repository.RepositoryInMemory;

import java.util.HashMap;

public class ServiceUser {

    public RepositoryInMemory<Integer, User> repository;

    public ServiceUser(RepositoryInMemory<Integer, User> repository){
        this.repository = repository;
    }

    public User findOne(Integer id) {
        return repository.findOne(id);
    }

    public HashMap<Integer, User> getAllUsers(){
        return repository.getAll();
    }

    public void executeValidation(User user) {
        user.getValidator().validate(user);
    }

    public void create(String[] fullName, Integer age, Integer nextID, String password, String email, Validator<User> validator) {

        User userToCreate = new User(fullName[0], fullName[1], age, password, email, (ValidatorUser) validator);
        userToCreate.setId(nextID);

        executeValidation(userToCreate);
        repository.create(userToCreate);

        nextID += 1;
    }

    public void deleteUser(Integer id) {
        repository.delete(id);
    }

    public User update(User user) {
        executeValidation(user);
        return repository.update(user);
    }
}

