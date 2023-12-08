package com.example.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class BookDetailsController {
    @FXML
    private Button borrowButton;

    public void borrow(ActionEvent event) {
        try {
            // Load the FXML file for the reservation
            FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
            Parent root = loader.load();

            // Create a new dialog
            Dialog<Void> dialog = new Dialog<>();
            dialog.initStyle(StageStyle.UNDECORATED);

            // Set the content of the dialog to the loaded FXML
            dialog.getDialogPane().setContent(root);

            // Get the Stage from the current Node
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the owner of the dialog to the current stage
            dialog.initOwner(currentStage);
            // Show the dialog
            dialog.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
}
