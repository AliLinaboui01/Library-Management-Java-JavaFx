package com.example.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SuccessReservationController {

    public void back(ActionEvent e){
        try {

//            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookDetails.fxml"));
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
//            currentStage.setFullScreen(true);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.close();

        } catch (Exception ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
}
