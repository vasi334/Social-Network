package com.example.lab4try.repository;

import com.example.lab4try.domain.CurrentUser;
import com.example.lab4try.domain.Entity;
import com.example.lab4try.domain.User;
import java.util.HashMap;

public class RepositoryInMemory<ID, E extends Entity<ID>> implements Repository<ID, E> {

    public HashMap<ID, E> dictionarOfEntities = new HashMap<>();

    @Override
    public HashMap<ID, E> getAll() {
        return dictionarOfEntities;
    }

    @Override
    public E findOne(ID id) {
        return dictionarOfEntities.get(id);
    }

    @Override
    public void create(E entity) {
        dictionarOfEntities.put(entity.getId(), entity);
    }

    @Override
    public void delete(ID id) {
        dictionarOfEntities.remove(id);
    }

    @Override
    public E update(E entity) {
        dictionarOfEntities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void saveFriendshipRequest(ID firstFriendID, ID secondFriendID) {

    }

    @Override
    public void deleteFriendshipRequest(ID firstFriendID, ID secondFriendID) {

    }

    @Override
    public void saveFriendship(ID firstFriendID, ID secondFriendID){

    }

    @Override
    public void cascadeDeleteFriendships(ID firstFriendID) {

    }

    @Override
    public boolean checkAlreadySentFriendRequest(ID firstFriendID, ID secondFriendID) {
        return false;
    }

    @Override
    public void acceptFriendshipRequest(ID firstFriendID, ID secondFriendID) {

    }

    @Override
    public boolean checkAlreadyFriends(ID firstFriendID, ID secondFriendID) {
        return false;
    }

    @Override
    public HashMap<ID, E> getAllFriends(CurrentUser user) {
        return null;
    }

    @Override
    public HashMap<ID, E> getAllFriendRequests(CurrentUser user) {
        return null;
    }

    @Override
    public void deleteFriendship(ID firstFriendID, ID secondFriendID) {

    }
}
