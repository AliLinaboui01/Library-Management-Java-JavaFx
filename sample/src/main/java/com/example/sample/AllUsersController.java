package com.example.sample;

import BD.DataBase;
import Session.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Book;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AllUsersController implements Initializable {
    @FXML
    private VBox AllUseresVbox;
    private List<User> users;

    @FXML
    private Button username;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(SessionManager.getCurrentUser());
        users = getUsers();
        List<User> allUseres = users;

        for (User user : allUseres) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("UserCompenent.fxml"));
            HBox userBox;
            try {
                userBox= fxmlLoader.load();
                UserCompenentController userCompenentController = fxmlLoader.getController();
                userCompenentController.setData(user);
                AllUseresVbox.getChildren().add(userBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }


    }

    public List<User> getUsers() {
        DataBase dataBase = new DataBase();
        Connection connection = dataBase.connect();
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                User user = new User();
                user.setCne(resultSet.getString("Cne"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName") );
               user.setImage(resultSet.getString("image"));
               // user.setImage(resultSet.getString("image"));

                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(resultSet.getString("password"));
                user.setUserID(resultSet.getInt("userID"));
                user.setUsername(resultSet.getString("username"));
                user.setUserType(resultSet.getString("userType"));
                // Note: Assuming "createdDate" is a date or timestamp column in your database
                user.setCreatedDate(new Date());
                userList.add(user);
            }
            dataBase.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @FXML
    private void goToFormAddUser(ActionEvent event) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fromAddUser.fxml"));
            Parent root = loader.load();
           UserCrudController editUserController = new UserCrudController();
            loader.setController(editUserController);
            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene on the stage
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);

            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
    public void logout(ActionEvent event){
        try {
            SessionManager.clearSession();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(nextScene);
            currentStage.show();
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
    public void goToHome(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminHome.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);
            currentStage.show();
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
    public void goToBooks(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allBooksAdmin.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);
            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }

    public void goToAllUsers(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allusers.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);
            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
    public void goToReservation(ActionEvent event){
        try {
            SessionManager.clearSession();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allReservationsAdmin.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);
            currentStage.show();
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }

}
