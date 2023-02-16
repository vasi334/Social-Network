package com.example.lab4try.controller;

import com.example.lab4try.HelloApplication;
import com.example.lab4try.domain.User;
import com.example.lab4try.domain.validators.ValidatorUser;
import com.example.lab4try.repository.*;
import com.example.lab4try.service.ServiceDB;
import com.example.lab4try.service.ServiceNetwork;
import com.example.lab4try.service.ServicePrincipal;
import com.example.lab4try.service.ServiceUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AuthenticationController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label welcomeText;
    @FXML
    private Label welcomeText2;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private ListView<String> listView;

    RepositoryInMemory<Integer, User> repository;

    RepositoryFriendship repositoryFriendship;

    Repository<Integer, User> repositoryDatabase;

    RepositoryPrincipal<Integer, User> repositoryPrincipal;

    ServiceNetwork serviceNetwork;

    ServiceUser serviceUser;

    ServicePrincipal<Integer> servicePrincipal;

    ServiceDB serviceDB;

    ValidatorUser validatorUser;

//    public AuthenticationController() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(this);
//        fxmlLoader.load();
//    }

    public void setRepository(RepositoryInMemory<Integer, User> repository) {
        this.repository = repository;
    }

    public void setRepositoryFriendship(RepositoryFriendship repositoryFriendship) {
        this.repositoryFriendship = repositoryFriendship;
    }

    public void setRepositoryDatabase(Repository<Integer, User> repositoryDatabase) {
        this.repositoryDatabase = repositoryDatabase;
    }

    public void setRepositoryPrincipal(RepositoryPrincipal<Integer, User> repositoryPrincipal) {
        this.repositoryPrincipal = repositoryPrincipal;
    }

    public void setServiceNetwork(ServiceNetwork serviceNetwork) {
        this.serviceNetwork = serviceNetwork;
    }

    public void setServiceUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    public void setServicePrincipal(ServicePrincipal<Integer> servicePrincipal) {
        this.servicePrincipal = servicePrincipal;
    }

    public void setValidatorUser(ValidatorUser validatorUser) {
        this.validatorUser = validatorUser;
    }

//    @FXML
//    protected void onHelloButtonClick(ActionEvent event) throws IOException {
//        String username = usernameInput.getText();
//        String password = passwordInput.getText();
//
////        if(serviceDB.checkUserCredentials(username, password)){
////            welcomeText.setText("Hello, " + username + "!");
////
////            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user-menu-view.fxml")));
////            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
////            scene = new Scene(root);
////            stage.setScene(scene);
////            stage.show();
//            //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-menu-view.fxml"));
//
////            Scene scene = new Scene(fxmlLoader.load(), 400, 240);
////            stage.setTitle("Hello!");
////            stage.setScene(scene);
////            stage.show();
//        }
//        else{
//            welcomeText.setText("This user does not exist. Please try again.");
//        }
//    }

    @FXML
    public void onListButtonClick(){
        welcomeText2.setText("Bravo!");

        ObservableList<String> items = FXCollections.observableArrayList();

        HashMap<Integer, User> allUsers = this.repositoryDatabase.getAll();
        List<User> values = allUsers.values()
                .stream().toList();

        for (User user : values) {
            items.add(user.toString());
        }

        items.add("Vasi");
        items.add("Alexia");

        listView.setItems(items);

        listView.getItems().addAll(items);
    }

    protected boolean checkUserCredentials(String username, String password){
        HashMap<Integer, User> allUsers = this.repositoryDatabase.getAll();

        List<Integer> keys = allUsers.keySet()
                .stream().toList();

        List<User> values = allUsers.values()
                .stream().toList();

        for (User user: values) {
            if(Objects.equals(user.getFirstName(), username) && Objects.equals(user.getPassword(), password)){
                return true;
            }
        }
        return false;
    }
}
