package com.example.lab4try;

import com.example.lab4try.domain.Network;
import com.example.lab4try.domain.User;
import com.example.lab4try.domain.validators.ValidatorUser;
import com.example.lab4try.repository.RepositoryFriendship;
import com.example.lab4try.repository.RepositoryInMemory;
import com.example.lab4try.repository.RepositoryPrincipal;
import com.example.lab4try.service.ServiceNetwork;
import com.example.lab4try.service.ServicePrincipal;
import com.example.lab4try.service.ServiceUser;
import com.example.lab4try.ui.UserInterface;

public class Main {
    public static void main(String[] args) {

        Network network = Network.getInstance();

        RepositoryInMemory<Integer, User> repository = new RepositoryInMemory<>();

        RepositoryFriendship repositoryFriendship = new RepositoryFriendship();

        RepositoryPrincipal<Integer, User> repositoryPrincipal = new RepositoryPrincipal<>(repository, repositoryFriendship);

        ServiceNetwork serviceNetwork = new ServiceNetwork(repositoryFriendship);

        ServiceUser serviceUser = new ServiceUser(repository);

        ServicePrincipal<Integer> servicePrincipal = new ServicePrincipal<>(serviceNetwork, serviceUser);

        ValidatorUser validatorUser = new ValidatorUser();

        UserInterface<Integer, User, User> ui = new UserInterface<>(repositoryPrincipal, servicePrincipal, validatorUser);

        ui.run();

        User user5 = new User("A", "A", 20, "parola", "AA@yahoo.com", validatorUser);
    }
}