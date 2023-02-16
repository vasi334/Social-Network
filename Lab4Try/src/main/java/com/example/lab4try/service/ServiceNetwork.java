package com.example.lab4try.service;

import com.example.lab4try.repository.RepositoryFriendship;

import java.util.ArrayList;

public class ServiceNetwork {
    public RepositoryFriendship repositoryFriendship;

    public ServiceNetwork(RepositoryFriendship repositoryFriendship) {
        this.repositoryFriendship = repositoryFriendship;
    }

    public RepositoryFriendship getRepositoryFriendship() {
        return repositoryFriendship;
    }

    public void setRepositoryFriendship(RepositoryFriendship repositoryFriendship) {
        this.repositoryFriendship = repositoryFriendship;
    }

    public void addListOfFriendships() {
        repositoryFriendship.getAllFriendships().add(new ArrayList<>());
    }

    public void addFriendToUser(Integer idOfFriend1, Integer idOfFriend2) {
        repositoryFriendship.getUserFriendships(idOfFriend1).add(idOfFriend2);
        repositoryFriendship.getUserFriendships(idOfFriend2).add(idOfFriend1);
    }

    public void deleteFriendFromUser(Integer idOfFriend1, Integer idOfFriend2) {
        repositoryFriendship.getUserFriendships(idOfFriend1).remove(idOfFriend2);
        repositoryFriendship.getUserFriendships(idOfFriend1).remove(idOfFriend2);
    }

    public void cascadeDeleteFriendships(Integer idOfDeletedUser){

        for(int i = 0; i < repositoryFriendship.getAllFriendships().size(); i++){
            repositoryFriendship.getUserFriendships(i).remove(idOfDeletedUser);
        }

        repositoryFriendship.getUserFriendships(idOfDeletedUser).clear();
    }
}
