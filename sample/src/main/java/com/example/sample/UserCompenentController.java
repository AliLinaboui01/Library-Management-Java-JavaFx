package com.example.sample;
import BD.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Book;
import model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserCompenentController {

    @FXML
    private Label CreatedDate;

    @FXML
    private Label UserEmail;

    @FXML
    private ImageView UserImage;

    @FXML
    private Label UserName;

    @FXML
    private Label UserType;
    private int user_id = 0;
    User this_user ;
   public void UserCompenentController(){};
    public void setData(User user) {
        UserName.setText(user.getFirstName() + " " + user.getLastName());
        UserType.setText(user.getUserType());
        UserEmail.setText(user.getEmail());
        CreatedDate.setText(String.valueOf(user.getCreatedDate()));

        this_user = user;

        // Set default image if the user's image path is null
        String imageUrl = user.getImage();
        if (imageUrl == null || imageUrl.isEmpty()) {
            UserImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/831.jpg"))));
        } else {
            try {
                // Load user's image
                InputStream stream = getClass().getResourceAsStream(imageUrl);
                if (stream != null) {
                    UserImage.setImage(new Image(stream));
                } else {
                    // Provide a default image if the stream is null or invalid
                    UserImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/831.jpg"))));
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception (e.g., log an error message)
                // Provide a default image in case of an exception
                UserImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/831.jpg"))));
            }
        }
    }

    // Your existing code...


    @FXML
    private void editUser(ActionEvent event)  {
        int id = this_user.getUserID();
        System.out.println(id);
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editUserByAdmin.fxml"));

            EditUserByAdminController editUserController = new EditUserByAdminController(this_user);
            loader.setController(editUserController);
            // Create a new scene
            Parent root = loader.load();

            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            // Set the new scene on the stage
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);

            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }

    @FXML
    void goToPageUser(ActionEvent event)  {
        int id = this_user.getUserID();
        System.out.println(id);
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editUserByAdmin.fxml"));

            EditUserByAdminController editUserController = new EditUserByAdminController(this_user);
            loader.setController(editUserController);
            // Create a new scene
            Parent root = loader.load();

            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            // Set the new scene on the stage
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);

            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
@FXML
    void goToPageAllUser(ActionEvent event)  {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allusers.fxml"));


            // Create a new scene
            Parent root = loader.load();

            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);

            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }

  /* void removeUser(ActionEvent event) {
    int userIdToRemove =this_user.getUserID(); // Assuming user is an instance variable
    System.out.println("Removing user with ID: " + userIdToRemove);

    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Confirmation");
    confirmationAlert.setHeaderText("Confirm User Deletion");
    confirmationAlert.setContentText("Are you sure you want to delete this user?");

    // Custom styling for the confirmation dialog (optional)
  //  confirmationAlert.getDialogPane().getStylesheets().add(
          //  getClass().getResource("path/to/your/custom/styles.css").toExternalForm());

    Optional<ButtonType> result = confirmationAlert.showAndWait();


    try {
        // Load the confirmation dialog from the custom FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("path/to/your/confirmationOperation.fxml"));
        Parent root = loader.load();

        // Create the stage for the confirmation dialog
        Stage confirmationStage = new Stage();
        confirmationStage.initStyle(StageStyle.UNDECORATED);
        confirmationStage.initModality(Modality.APPLICATION_MODAL);
        confirmationStage.setScene(new Scene(root));

        // Access the controller of the confirmation dialog
        ConfirmationOperationController confirmationController = loader.getController();

        // You may pass data to the controller if needed
        confirmationController.setUserData(user);

        // Show the confirmation dialog and wait for the user's response
        confirmationStage.showAndWait();

        // Retrieve the result from the confirmation dialog
        boolean userConfirmedDeletion = confirmationController.isConfirmed();

        // Check if the user confirmed deletion




    if (result.isPresent() && result.get() == ButtonType.OK) {

        DataBase dataBase = new DataBase();
        Connection connection = dataBase.connect();
            String sql = "DELETE FROM Users WHERE userID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, userIdToRemove);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("User removed successfully from the database.");
                    // Optionally, update your UI or take other actions after successful removal
                    // For example, refresh the user interface to reflect the changes
                    // updateUI();
                    goToPageUser(event); // You might want to navigate to another page after removal
                } else {
                    System.out.println("No user found with the specified ID.");
                    // Optionally, show a message indicating that no user was found
                }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception
            // Optionally, show an error message to the user
        }
    }

    }

    private void goToPageUser(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("allusers.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add User");
            stage.setScene(new Scene(root, 900, 700));
            stage.setFullScreen(true);
            // You can pass any necessary data to the controller of the new FXML if needed
            //  AllUsersController formAddUserController = fxmlLoader.getController();
            // formAddUserController.setSomeData(someData);

            stage.show();

            // Optionally, you can close the current stage if needed
            // ((Stage) someNode.getScene().getWindow()).close();
        } catch (IOException e) {
            System.out.println(e.getMessage()); // Handle the exception
        }

    }

   */
  public void removeUser(ActionEvent event) throws IOException {
      int id = this_user.getUserID();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmationOperation.fxml"));
      Parent root = loader.load();
      ConfirmationOperationController confirmationController = loader.getController();
      confirmationController.setUserId(this_user.getUserID());

      Dialog<Boolean> dialog = new Dialog<>();
      dialog.initStyle(StageStyle.TRANSPARENT);
      dialog.getDialogPane().setContent(root);
      dialog.setResultConverter(buttonType -> buttonType == ButtonType.OK);
      Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      dialog.initOwner(currentStage);
      Optional<Boolean> result = dialog.showAndWait();

      // Check if the user confirmed the operation

  }

    void removeUserFromDatabase(int userIdToRemove) throws SQLException {
        DataBase dataBase = new DataBase();
        Connection connection = dataBase.connect();
            String sql = "DELETE FROM Users WHERE userID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, userIdToRemove);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("User removed successfully from the database.");
                } else {
                    System.out.println("No user found with the specified ID.");
                }

        }
    }

}
