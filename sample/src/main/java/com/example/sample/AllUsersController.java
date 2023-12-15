package com.example.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Book;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AllUsersController implements Initializable {
    @FXML
    private VBox AllUseresVbox;
    private List<User> users;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        users = getUsers();


        for (User user : users) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("UserCompenent.fxml"));
            HBox userBox;
            try {
                userBox= fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            UserCompenentController userCompenentController = fxmlLoader.getController();
            userCompenentController.setData(user);
            AllUseresVbox.getChildren().add(userBox);

        }


    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();

        User user = new User();
        user.setCne("CNE123");
        user.setEmail("user1@example.com");
        user.setFirstName("John");
        user.setImage("imgs/aliProfile.png");
        user.setLastName("Doe");
        user.setPassword("password1");
        user.setUsername("john_doe");
        user.setUserType("regular");
        user.setCreatedDate(new Date()); // Set the createdDate for user1
        userList.add(user);

        user = new User();
        user.setCne("CNE456");
        user.setEmail("user2@example.com");
        user.setFirstName("Jane");
        user.setImage("imgs/aliProfile.png");
        user.setLastName("Smith");
        user.setPassword("password2");
        user.setUsername("jane_smith");
        user.setCreatedDate(new Date()); // Set the createdDate for user1
        user.setUserType("admin");
        userList.add(user);


        user = new User();
        user.setCne("CNE456");
        user.setEmail("user2@example.com");
        user.setFirstName("Jane");
        user.setImage("imgs/aliProfile.png");
        user.setLastName("Smith");
        user.setPassword("password2");
        user.setUsername("jane_smith");
        user.setUserType("Student");
        user.setCreatedDate(new Date()); // Set the createdDate for user1
        userList.add(user);



        user = new User();
        user.setCne("CNE456");
        user.setEmail("user2@example.com");
        user.setFirstName("Jane");
        user.setImage("imgs/aliProfile.png");
        user.setLastName("Smith");
        user.setPassword("password2");
        user.setUsername("jane_smith");
        user.setCreatedDate(new Date()); // Set the createdDate for user1
        user.setUserType("admin");
        userList.add(user);





        user = new User();
        user.setCne("CNE456");
        user.setEmail("user2@example.com");
        user.setFirstName("Jane");
        user.setImage("imgs/aliProfile.png");
        user.setLastName("Smith");
        user.setPassword("password2");
        user.setUsername("jane_smith");
        user.setCreatedDate(new Date()); // Set the createdDate for user1
        user.setUserType("admin");
        userList.add(user);

        // Add more users as needed

        return userList;
    }

    @FXML
    private void goToFormAddUser(ActionEvent event) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fromAddUser.fxml"));
            Parent root = loader.load();

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


}
