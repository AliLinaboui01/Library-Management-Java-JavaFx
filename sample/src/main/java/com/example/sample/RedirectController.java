package com.example.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RedirectController {

    @FXML
    private AnchorPane yourAnchorPane;
    public void initialize() {
        // You can add any initialization logic here

        // Redirect to another page after a short delay (you can adjust the delay as needed)
        redirectToOtherPage();
    }
    private void redirectToOtherPage() {
        try {
            // Load the FXML file for the target scene (otherpage.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allusers.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) yourAnchorPane.getScene().getWindow(); // Replace with your actual anchor pane
            // Set the new scene on the stage
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);

            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
