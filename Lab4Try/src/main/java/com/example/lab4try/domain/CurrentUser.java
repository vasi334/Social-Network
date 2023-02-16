package com.example.lab4try.domain;

public class CurrentUser {
        private static CurrentUser single_instance = null;

        public Integer id;
        public String firstName;
        public String lastName;
        public String email;
        public String password;
        public Integer age;

        private CurrentUser() {}

        public static CurrentUser getInstance()
        {
            if (single_instance == null)
                single_instance = new CurrentUser();

            return single_instance;
        }

    public void setFirstName(String firstName) {
            this.firstName = firstName;
    }

    public void setLastName(String lastName) {
            this.lastName = lastName;
    }

    public void setPassword(String password) {
            this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}