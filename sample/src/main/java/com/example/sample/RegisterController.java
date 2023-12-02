package com.example.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class RegisterController {

    @FXML
    private PasswordField confPassword;

    @FXML
    private TextField email;

    @FXML
    private TextField name;
    @FXML
    private Button loginLabel;

    @FXML
    private PasswordField password;

    @FXML
    private Button registerButton;
    public void register(ActionEvent e){
        final String SSl_FACTORY  ="";

    }
    public void goLogin(ActionEvent e){
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            currentStage.setScene(nextScene);
            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
}
