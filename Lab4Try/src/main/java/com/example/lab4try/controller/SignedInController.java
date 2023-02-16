package com.example.lab4try.controller;

import com.example.lab4try.domain.CurrentUser;
// import com.example.lab4try.domain.Network;
import com.example.lab4try.domain.User;
import com.example.lab4try.domain.validators.ValidatorUser;
import com.example.lab4try.repository.RepositoryDatabase;
import com.example.lab4try.repository.RepositoryFriendship;
import com.example.lab4try.repository.RepositoryInMemory;
import com.example.lab4try.repository.RepositoryPrincipal;
import com.example.lab4try.service.ServiceDB;
import com.example.lab4try.service.ServiceNetwork;
import com.example.lab4try.service.ServicePrincipal;
import com.example.lab4try.service.ServiceUser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class SignedInController implements Initializable {

    //Network network = Network.getInstance();

    // Repositories

    RepositoryInMemory<Integer, User> repository = new RepositoryInMemory<>();

    RepositoryFriendship repositoryFriendship = new RepositoryFriendship();

    RepositoryDatabase userRepo = new RepositoryDatabase("jdbc:postgresql://localhost:5432/SocialNetwork",
            "postgres", "vasi", new ValidatorUser());

    RepositoryPrincipal<Integer, User> repositoryPrincipal = new RepositoryPrincipal<>(userRepo, repositoryFriendship);


    //Services

    ServiceNetwork serviceNetwork = new ServiceNetwork(repositoryFriendship);

    ServiceUser serviceUser = new ServiceUser(repository);

    ServiceDB serviceDB = new ServiceDB(userRepo);

    ServicePrincipal<Integer> servicePrincipal = new ServicePrincipal<>(serviceNetwork, serviceUser);

    //Validator

    ValidatorUser validatorUser = new ValidatorUser();

    @FXML
    private User currentUser;

    @FXML
    TableView<User> tableUsers;

    @FXML
    TableView<User> tableFriends;

    @FXML
    TableView<User> tableFriendRequests;

    /**
     * Coloanele tabelului tableUsers
     */
    @FXML
    TableColumn<User, String> columnFirstName;

    @FXML
    TableColumn<User, String>  columnLastName;

    @FXML
    TableColumn<User, String>  columnUserStatus;

    /**
     * Coloanele tabelului tableFriends
     */
    @FXML
    TableColumn<User, String> columnFirstNameFriend;

    @FXML
    TableColumn<User, String>  columnLastNameFriend;

    /**
     * Coloanele tabelului tableFriendRequests
     */
    @FXML
    TableColumn<User, String> columnFirstNameFriendRequest;

    @FXML
    TableColumn<User, String>  columnLastNameFriendRequest;

    @FXML
    Button sendFriendRequestButton;

    @FXML
    Button acceptFriendRequestButton;

    @FXML
    Button deleteFriendRequestButton;

    @FXML
    Button removeFriendButton;

    private void initCurrentUser(){
        CurrentUser user = CurrentUser.getInstance();
        currentUser = serviceDB.getOneUser(user.getFirstName(), user.getPassword());
        if(currentUser != null)
        {
            currentUser.setId(serviceDB.getOneUser(currentUser.getFirstName(), currentUser.getPassword()).getId());
            user.setId(currentUser.getId());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCurrentUser();

        if(currentUser != null)
        {
            populateUsers();
            populateFriends();
            populateFriendRequests();
        }
    }

    /**
     * Adauga entitati in tabelul tableFriends
     */
    private void populateFriends() {

        this.columnFirstNameFriend.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        this.columnLastNameFriend.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        ObservableList<User> friends = FXCollections.observableArrayList();

        List<User> listOfFriends = new ArrayList<>(this.serviceDB.getAllFriends(CurrentUser.getInstance()).values());

        for(User user: listOfFriends){
            if(!user.equals(this.currentUser)){
                friends.add(user);
            }
        }

        tableFriends.getItems().addAll(friends);
    }

    private void populateFriendRequests() {
        this.columnFirstNameFriendRequest.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        this.columnLastNameFriendRequest.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        ObservableList<User> friendRequests = FXCollections.observableArrayList();

        List<User> listOfFriends = new ArrayList<>(this.serviceDB.getAllFriendRequests(CurrentUser.getInstance()).values());

        for(User user: listOfFriends){
            if(!user.equals(this.currentUser)){
                friendRequests.add(user);
            }
        }

        tableFriendRequests.getItems().addAll(friendRequests);
    }

    /**
     * Adauga entitati in tabelul tableUsers
     */

    private void populateUsers(){

        this.columnFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        this.columnLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        ObservableList<User> users = FXCollections.observableArrayList();

        List<User> listOfUsers = new ArrayList<>(this.serviceDB.getAllUsers().values());

        for(User user: listOfUsers){
            if(!user.equals(this.currentUser)){
                users.add(user);
            }
        }

        tableUsers.getItems().addAll(users);
    }

    @FXML
    public void sendFriendRequestHandle()
    {
        if(tableUsers.getSelectionModel().getSelectedItem() != null)
        {
            if(!serviceDB.checkAlreadySendFriendRequest(currentUser.getId(),
                    tableUsers.getSelectionModel().getSelectedItem().getId())
                    && !serviceDB.checkAlreadyFriends(currentUser.getId(),
                    tableUsers.getSelectionModel().getSelectedItem().getId()))
            {
                serviceDB.saveFriendshipRequest(currentUser, tableUsers.getSelectionModel().getSelectedItem());
                tableFriendRequests.getItems().add(tableUsers.getSelectionModel().getSelectedItem());
            }
        }
    }

    @FXML
    public void deleteFriendRequestHandle()
    {
        if(tableFriendRequests.getSelectionModel().getSelectedItem() != null)
        {
            serviceDB.deleteFriendshipRequest(currentUser, tableFriendRequests.getSelectionModel().getSelectedItem());
            tableFriendRequests.getItems().remove(tableFriendRequests.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public void acceptFriendRequestHandle()
    {
        if(tableFriendRequests.getSelectionModel().getSelectedItem() != null)
        {
            serviceDB.acceptFriendshipRequest(currentUser, tableFriendRequests.getSelectionModel().getSelectedItem());
            serviceDB.deleteFriendshipRequest(currentUser, tableFriendRequests.getSelectionModel().getSelectedItem());
            tableFriends.getItems().add(tableFriendRequests.getSelectionModel().getSelectedItem());
            tableFriendRequests.getItems().remove(tableFriendRequests.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public void removeFriendHandle()
    {
        if(tableFriends.getSelectionModel().getSelectedItem() != null)
        {
            serviceDB.deleteFriendship(currentUser, tableFriends.getSelectionModel().getSelectedItem());
            tableFriends.getItems().remove(tableFriends.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public void onEnterSearchUser(KeyEvent e){
        System.out.println("keyStroke") ;
    }
}
