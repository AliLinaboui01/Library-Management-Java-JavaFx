package com.example.sample;

import BD.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConfirmationOperationController {
    int id;
    public ConfirmationOperationController(int id) {
        this.id = id;
    }
    public ConfirmationOperationController() {

    }
   public void setUserId(int id) {
        this.id = id;
   }
   public int getId(){return id;}
    @FXML
    private boolean confirmed = false; // Default value is false

    // ... other code ...

    @FXML
    private void isConfirmed(ActionEvent event) throws SQLException {
        confirmed = true;
        System.out.println("from cConfirm "+ getId());
        id = getId();
        UserCompenentController userCompenentController = new UserCompenentController();
        userCompenentController.removeUserFromDatabase(id);
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allusers.fxml"));


            // Create a new scene
            Parent root = loader.load();

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
        //closeDialog(event);
    }

    @FXML
    private void isCancel(ActionEvent event) {
        // No need to explicitly set confirmed to false since it's already false by default
        System.out.println("from cancel "+ getId());
        closeDialog(event);
    }

    private void closeDialog(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public boolean isConfirmed() {

        return confirmed;
    }
}
