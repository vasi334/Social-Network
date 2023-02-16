package com.example.lab4try.service;

import com.example.lab4try.domain.CurrentUser;
import com.example.lab4try.domain.User;
import com.example.lab4try.domain.validators.ValidatorUser;
import com.example.lab4try.repository.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ServiceDB {
    Repository<Integer, User> repositoryDB;

    ValidatorUser validatorUser;

    //Constructor
    public ServiceDB(Repository<Integer, User> repositoryDB) {
        this.repositoryDB = repositoryDB;
    }

    //Getters
    public Repository<Integer, User> getRepositoryDB() {
        return repositoryDB;
    }

    //Setters
    public void setRepositoryDB(Repository<Integer, User> repositoryDB) {
        this.repositoryDB = repositoryDB;
    }

    //Methods
    public HashMap<Integer, User> getAllUsers(){
        return repositoryDB.getAll();
    }

    public HashMap<Integer, User> getAllFriends(CurrentUser user){
        return repositoryDB.getAllFriends(user);
    }

    public User getOneUser(String firstName, String password){
        HashMap<Integer, User> allUsers = this.getAllUsers();

        List<User> values = allUsers.values()
                .stream().toList();

        User userToReturn = null;

        for (User user: values) {
            if(Objects.equals(user.getFirstName(), firstName) && Objects.equals(user.getPassword(), password)){
                userToReturn = user;
            }
        }

        return userToReturn;
    }

    public boolean checkUserCredentials(String username, String password){
        HashMap<Integer, User> allUsers = this.getAllUsers();

        List<User> values = allUsers.values()
                .stream().toList();

        for (User user: values) {
            if(Objects.equals(user.getFirstName(), username) && Objects.equals(user.getPassword(), password)){
                return true;
            }
        }
        return false;
    }

    public boolean checkAlreadySendFriendRequest(Integer idFriend1, Integer idFriend2){
        return repositoryDB.checkAlreadySentFriendRequest(idFriend1, idFriend2);
    }

    public boolean checkAlreadyFriends(Integer idFriend1, Integer idFriend2){
        return repositoryDB.checkAlreadyFriends(idFriend1, idFriend2);
    }

    public void saveUser(String firstName, String lastName, Integer age, String password, String email){
        repositoryDB.create(new User(firstName, lastName, age, password, email, validatorUser));
    }

    public void saveFriendship(User firstFriendID, User secondFriendID){
        repositoryDB.saveFriendship(firstFriendID.getId(), secondFriendID.getId());
    }

    public void deleteFriendship(User firstFriendID, User secondFriendID){
        repositoryDB.deleteFriendship(firstFriendID.getId(), secondFriendID.getId());
    }

    public void saveFriendshipRequest(User firstFriendID, User secondFriendID){
        repositoryDB.saveFriendshipRequest(firstFriendID.getId(), secondFriendID.getId());
    }

    public void deleteFriendshipRequest(User firstFriendID, User secondFriendID) {
        repositoryDB.deleteFriendshipRequest(firstFriendID.getId(), secondFriendID.getId());
    }

    public void acceptFriendshipRequest(User firstFriendID, User secondFriendID) {
        repositoryDB.acceptFriendshipRequest(firstFriendID.getId(), secondFriendID.getId());
    }

    public void cascadeDeleteFriendships(User firstFriendID){
        repositoryDB.cascadeDeleteFriendships(firstFriendID.getId());
    }

    public HashMap<Integer, User> getAllFriendRequests(CurrentUser user) {
        return repositoryDB.getAllFriendRequests(user);
    }
}
