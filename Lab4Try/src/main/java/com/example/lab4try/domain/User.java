package com.example.lab4try.domain;


import com.example.lab4try.domain.validators.Validator;
import com.example.lab4try.domain.validators.ValidatorUser;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Objects;

public class User extends Entity<Integer>{
    Validator<User> validator;
    String firstName;
    String lastName;
    Integer age;

    String email;
    String password;

    private final ObjectProperty<String> firstNameProp = new SimpleObjectProperty<>();
    private final ObjectProperty<String> lastNameProp = new SimpleObjectProperty<>();

    // Constructor

    public User(String firstName, String lastName, Integer age, String password, String email, ValidatorUser validator) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.password = password;
        this.email = email;
        this.validator = validator;
    }

    // Setters

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // Getters

    public String getPassword() { return password; }

    public String getFirstName() { return firstName; }

    public String getEmail() {
        return email;
    }

    public final ObjectProperty<String> firstNameProperty() {
        firstNameProp.set(firstName);
        return firstNameProp;
    }

    public final ObjectProperty<String> lastNameProperty() {
        lastNameProp.set(lastName);
        return lastNameProp;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public Validator<User> getValidator() {
        return validator;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) &&
                Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }
}
