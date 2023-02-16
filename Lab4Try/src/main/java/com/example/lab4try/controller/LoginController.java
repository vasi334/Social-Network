package com.example.lab4try.controller;

import com.example.lab4try.domain.CurrentUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Button buttonLogin, buttonSignup;

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label welcomeText;

    CurrentUser currentUser = CurrentUser.getInstance();

    public void handleLogin() throws Exception{

        String firstName = usernameInput.getText();
        String password = passwordField.getText();

        currentUser.setFirstName(firstName);
        currentUser.setPassword(password);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lab4try/signed-in-view.fxml"));
        Parent root = loader.load();

        SignedInController signedInController = loader.getController();

        if(signedInController.serviceDB.checkUserCredentials(firstName, password))
        {
            currentUser.setId(signedInController.serviceDB.getOneUser(firstName, password).getId());
            Stage window = (Stage) buttonLogin.getScene().getWindow();
            window.setScene(new Scene(root,600, 500));
        }
        else
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("You have entered wrong credentials!");
            errorAlert.showAndWait();
        }
    }

    public void handleSignup() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/lab4try/signup-view.fxml"));

        Stage window = (Stage) buttonSignup.getScene().getWindow();
        window.setScene(new Scene(root,600, 500));
    }
}