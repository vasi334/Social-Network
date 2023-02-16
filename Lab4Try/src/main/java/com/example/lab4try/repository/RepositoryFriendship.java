package com.example.lab4try.repository;

import java.util.ArrayList;

public class RepositoryFriendship{
    public ArrayList<ArrayList<Integer>> usersFriendships;

    public RepositoryFriendship() {
        this.usersFriendships = new ArrayList<>();
    }

    public ArrayList<ArrayList<Integer>> getUsersFriendships() {
        return usersFriendships;
    }

    public void setUsersFriendships(ArrayList<ArrayList<Integer>> usersFriendships) {
        this.usersFriendships = usersFriendships;
    }

    public ArrayList<ArrayList<Integer>> getAllFriendships() {
        return usersFriendships;
    }

    public ArrayList<Integer> getUserFriendships(Integer index) {
        return usersFriendships.get(index);
    }
}