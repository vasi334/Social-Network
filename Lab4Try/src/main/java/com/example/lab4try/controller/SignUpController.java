package com.example.lab4try.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

public class SignUpController {
    @FXML
    private TextField firstNameField, lastNameField, emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label message;

    @FXML
    private DatePicker ageField;

    @FXML
    private Hyperlink backToLogin;

    /**
     * Functionalitatea de Sign Up
     * @throws IOException Exceptie daca nu se poate incarca SignedInController
     */
    public void handleSignup() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lab4try/signed-in-view.fxml"));
        Parent root = loader.load();
        SignedInController signedInController = loader.getController();


        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        DatePicker age = ageField;
        String password = passwordField.getText();

        if(!signedInController.serviceDB.checkUserCredentials(firstName, password))
        {
            signedInController.serviceDB.saveUser(firstName, lastName,
                    Period.between(age.getValue(), LocalDate.now()).getYears(), password, email);
            message.setText("Signed-up!");
        }
        else
        {
            message.setText("Try again!");
        }
    }

    public void handleLogin() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lab4try/hello-view.fxml"));
        Parent root = loader.load();

        Stage window = (Stage) backToLogin.getScene().getWindow();
        window.setScene(new Scene(root,600, 500));
    }
}