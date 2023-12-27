package com.example.sample;

import BD.DataBase;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField email;

    @FXML
    private Button loginButton;
    @FXML
    private Button registerLabel;

    @FXML
    private PasswordField password;
    public void login(ActionEvent e) {
        String name = email.getText();
        String pass = password.getText();
        DataBase dataBase = new DataBase();
        Connection connect = dataBase.connect();

        // Use a PreparedStatement to avoid SQL injection
        String sql = "SELECT * FROM user WHERE email=? AND password=?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Successful login logic goes here
                // Load the next FXML file
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Parent root = loader.load();

                    // Create a new scene
                    Scene nextScene = new Scene(root);

                    // Get the Stage from the current Node (you can adjust this if needed)
                    Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                    // Set the new scene on the stage
                    currentStage.setScene(nextScene);
                    currentStage.setFullScreen(true);

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
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle the SQL exception appropriately
        }
    }

    public void goRegister(ActionEvent e) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
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
