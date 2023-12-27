package com.example.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterVerificationController {
    @FXML
    private TextField codeVerification;
    private String verifyCode;
    @FXML
    private Button verify;



    @FXML
    private void handleVerifyButtonClick(ActionEvent event) {
        if (Objects.equals(codeVerification.getText(), verifyCode)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("verify.fxml"));
                Parent root = loader.load();

                // Create a new scene for the home page
                Scene homepageScene = new Scene(root);

                // Get the Stage from the current Node (you can adjust this if needed)
                Stage currentStage = (Stage) verify.getScene().getWindow();

                // Set the home page scene on the stage
                currentStage.setScene(homepageScene);
                currentStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
        }
    }
    public void  setVerifyCode(String verificationCode) {
        this.verifyCode = verificationCode;
    }

}



