//package com.example.lab4try.controller;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.stage.Stage;
//
//public class HelloApplicationController {
//    @FXML
//    private Button buttonLogin, buttonSignup;
//
//    public void handleLogin() throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/com/example/lab4try/signed-in-view.fxml"));
//
//        Stage window = (Stage) buttonLogin.getScene().getWindow();
//        window.setScene(new Scene(root,600, 500));
//    }
//
//    public void handleSignup() throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/com/example/lab4try/signup-view.fxml"));
//
//        Stage window = (Stage) buttonSignup.getScene().getWindow();
//        window.setScene(new Scene(root,600, 500));
//    }
//}