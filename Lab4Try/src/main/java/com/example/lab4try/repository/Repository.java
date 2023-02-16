package com.example.lab4try.repository;

import com.example.lab4try.domain.CurrentUser;
import com.example.lab4try.domain.Entity;
import com.example.lab4try.domain.User;

import java.util.HashMap;

/**
 *
 * @param <ID> - E must have an attribute of type ID
 * @param <E> - type of entities saved in repository
 */
public interface Repository<ID, E extends Entity<ID>> {
    HashMap<ID, E> getAll();

    /**
     *
     * @param id - id of the entity you want to be returned
     * @return - the object with the id = id(@param)
     */
    E findOne(ID id);

    /**
     * @param entity - the entity object you want to creat
     */
    void create(E entity);

    /**
     *
     * @param id - the id of the entity you want to delete
     * @return - null
     */
    void delete(ID id);

    /**
     *
     * @param entity - updates the entity
     * @return - the updated entity
     */
    E update(E entity);

    //void save(User utilizator);

    boolean checkAlreadySentFriendRequest(ID firstFriendID, ID secondFriendID);
    boolean checkAlreadyFriends(ID firstFriendID, ID secondFriendID);
    void saveFriendship(ID firstFriendID, ID secondFriendID);

    void deleteFriendship(ID firstFriendID, ID secondFriendID);

    void saveFriendshipRequest(ID firstFriendID, ID secondFriendID);

    void deleteFriendshipRequest(ID firstFriendID, ID secondFriendID);

    void cascadeDeleteFriendships(ID firstFriendID);

    void acceptFriendshipRequest(ID firstFriendID, ID secondFriendID);

    HashMap<ID, E> getAllFriends(CurrentUser user);

    HashMap<ID, E> getAllFriendRequests(CurrentUser user);
}
