package com.example.lab3;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class dashboardController {
    public Label demo;



    public void adminclick(ActionEvent actionEvent) {
        try {
            Parent secondScene = FXMLLoader.load(getClass().getResource("admin.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("dashboard");
            secondStage.setScene(new Scene(secondScene));
            Stage firstSceneStage = (Stage) demo.getScene().getWindow();
            firstSceneStage.close();


            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}
    public void employeeclick(ActionEvent actionEvent) {
        try {
            Parent secondScene = FXMLLoader.load(getClass().getResource("employee.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("dashboard");
            secondStage.setScene(new Scene(secondScene));
            Stage firstSceneStage = (Stage) demo.getScene().getWindow();
            firstSceneStage.close();


            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }


    public void logoutClick(ActionEvent actionEvent) {
        try {
            // Load the helloview FXML file
            Parent loginScene = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

            // Set up the new stage for the login screen
            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(loginScene));

            // Close the current dashboard window
            Stage currentStage = (Stage) demo.getScene().getWindow();
            currentStage.close();

            // Show the login screen
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



