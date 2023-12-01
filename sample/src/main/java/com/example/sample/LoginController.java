package com.example.sample;

import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField email;

    @FXML
    private Button loginButton;
    @FXML
    private Label registerLabel;

    @FXML
    private PasswordField password;
    public void login(ActionEvent e){
        String name = email.getText();
        String pass = password.getText();
        if (name.equals("ali") && pass.equals("ali123")) {
            // Load the next FXML file
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("library.fxml"));
                Parent root = loader.load();

                // Create a new scene
                Scene nextScene = new Scene(root);

                // Get the Stage from the current Node (you can adjust this if needed)
                Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                // Set the new scene on the stage
                currentStage.setScene(nextScene);

            } catch (IOException ex) {
                ex.printStackTrace(); // Handle the exception appropriately
            }

        } else {
            // Unsuccessful login logic goes here
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");

            alert.showAndWait();

        }
    }
    public void goRegister(ActionEvent e) {
        System.out.println("hi ali");
//        try {
            // Load the FXML file for the register scene
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
//            Parent root = loader.load();
//
//            // Create a new scene
//            Scene nextScene = new Scene(root);
//
//            // Get the Stage from the current Node (you can adjust this if needed)
//            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//
//            // Set the new scene on the stage
//            currentStage.setScene(nextScene);
//            currentStage.show();

//        } catch (IOException ex) {
//            ex.printStackTrace(); // Handle the exception appropriately
//        }
    }


}
