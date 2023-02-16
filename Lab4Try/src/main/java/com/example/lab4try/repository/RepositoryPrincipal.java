package com.example.lab4try.repository;

import com.example.lab4try.domain.Entity;

public class RepositoryPrincipal<ID, E extends Entity<ID>>{

    Repository<ID, E> repository;

    RepositoryFriendship repositoryFriendship;

    public RepositoryPrincipal(Repository<ID, E> repository, RepositoryFriendship repositoryFriendship) {
        this.repository = repository;
        this.repositoryFriendship = repositoryFriendship;
    }

    public Repository<ID, E> getRepositoryInMemory() {
        return repository;
    }

    public void setRepositoryInMemory(Repository<ID, E> repository) {
        this.repository = repository;
    }

    public RepositoryFriendship getRepositoryFriendship() {
        return repositoryFriendship;
    }

    public void setRepositoryFriendship(RepositoryFriendship repositoryFriendship) {
        this.repositoryFriendship = repositoryFriendship;
    }
}