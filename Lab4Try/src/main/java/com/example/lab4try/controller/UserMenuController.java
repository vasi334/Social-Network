package com.example.lab4try.controller;

import com.example.lab4try.domain.User;
import com.example.lab4try.repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class UserMenuController {
    Repository<Integer, User> repositoryDatabase;

    @FXML
    private Button button;
    @FXML
    private Label welcomeText;
    @FXML
    private ListView<String> listView;


    public void setRepositoryDatabase(Repository<Integer, User> repositoryDatabase) {
        this.repositoryDatabase = repositoryDatabase;
    }

    //    @FXML
//    public void setNewStage() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-menu-view.fxml"));
//
//        Stage stage = new Stage();
//        Scene scene = new Scene(fxmlLoader.load(), 400, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
    @FXML
    protected void onButtonClick() {
        welcomeText.setText("Bravo!");

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

//    @FXML
//    protected void onListViewButtonClick(){
//        listView.getItems().addAll(repositoryDatabase.getAll());
//    }
}
