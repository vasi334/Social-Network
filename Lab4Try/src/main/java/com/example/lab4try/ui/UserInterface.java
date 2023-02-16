package com.example.lab4try.ui;

import com.example.lab4try.domain.Entity;
import com.example.lab4try.domain.User;
import com.example.lab4try.domain.validators.Validator;
import com.example.lab4try.domain.validators.ValidatorUser;
import com.example.lab4try.repository.RepositoryPrincipal;
import com.example.lab4try.service.ServicePrincipal;

import java.util.HashMap;
import java.util.Scanner;

public class UserInterface<ID, E extends Entity<ID>, T> {
    public RepositoryPrincipal<ID, E> repository;
    public ServicePrincipal<ID> servicePrincipal;
    public Validator<T> validator;

    public Scanner sc;

    public UserInterface(RepositoryPrincipal<ID, E> repository, ServicePrincipal<ID> servicePrincipal, Validator<T> validator) {
        this.repository = repository;
        this.servicePrincipal = servicePrincipal;
        this.validator = validator;
        this.sc = new Scanner(System.in);
    }

    public void menu(){
        System.out.println("Enter the action you want to do: ");
        System.out.println("1. Enter users");
        System.out.println("2. Delete users");
        System.out.println("3. Add friendship/s");
        System.out.println("4. Delete friendship/s");
        System.out.println("5. Show all users");
        System.out.println("6. Exit");
    }

    public void run(){
        while(true){
            menu();

            System.out.println("Your option is: ");

            int option = sc.nextInt();

            if(option == 1){
                addUser();
            } else if (option == 2) {
                deleteUser();
            } else if (option == 3) {
                addFriendship();
            } else if (option == 4) {
                deleteFriendship();
            } else if (option == 5) {
                showAllUsers();
            } else if (option == 6) {
                break;
            }
        }
    }

    private void showAllUsers() {
        HashMap<Integer, User> allUsers = servicePrincipal.serviceUser.getAllUsers();

        for (User user : allUsers.values()) {
            System.out.println(user);
        }
    }

    public void addUser(){
        System.out.println("Enter the number of users you want to add: ");

        int numberOfUsers = sc.nextInt();

        int nextID = 0;

        for(int i = 0; i < numberOfUsers; i++)
        {
            System.out.println("Enter firstname, lastname, age and password: ");

            String[] string = new String [sc.nextInt()];

            sc.nextLine();

            for (int j = 0; j < string.length; j++)

            {
                string[j] = sc.nextLine();
            }

            int age = sc.nextInt();

            String password = sc.nextLine();

            String email = sc.nextLine();

            servicePrincipal.serviceUser.create(string, age, nextID, password, email, (ValidatorUser) validator);

            servicePrincipal.serviceNetwork.addListOfFriendships();

            nextID += 1;
        }
    }

    public void deleteUser(){
        System.out.println("Enter the number of users you want to delete: ");

        int numberOfUsersToDelete = sc.nextInt();

        for(int i = 0; i < numberOfUsersToDelete; i++)
        {
            System.out.println("Enter the ID: ");

            Integer ID = sc.nextInt();

            servicePrincipal.serviceUser.deleteUser(ID);

            servicePrincipal.serviceNetwork.cascadeDeleteFriendships(ID);
        }
    }

    public void addFriendship(){
        System.out.println("Enter the number of friendships you want to add: ");

        int numberOfFriendships = sc.nextInt();

        for(int i = 0; i < numberOfFriendships; i++)
        {
            System.out.println("Enter the first ID: ");

            Integer firstID = sc.nextInt();

            System.out.println("Enter the second ID: ");

            Integer secondID = sc.nextInt();

            servicePrincipal.serviceNetwork.addFriendToUser(firstID, secondID);
        }

    }

    public void deleteFriendship(){
        System.out.println("Enter the number of friendships you want to delete: ");

        int numberOfFriendshipsToDelete = sc.nextInt();

        for(int i = 0; i < numberOfFriendshipsToDelete; i++)
        {
            System.out.println("Enter the first ID: ");

            Integer firstID = sc.nextInt();

            System.out.println("Enter the second ID: ");

            Integer secondID = sc.nextInt();

            servicePrincipal.serviceNetwork.deleteFriendFromUser(firstID, secondID);
        }
    }
}