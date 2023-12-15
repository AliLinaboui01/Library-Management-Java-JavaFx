package com.example.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterVerificationController {
    @FXML
    private TextField codeVerification;

    @FXML
    private Button verify;
//    public RegisterVerificationController(String verificationCode) {
//        codeVerification.setText(verificationCode);
//        handleVerifyButtonClick();
//    }

//    private void handleVerifyButtonClick() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("verify.fxml"));
//            Parent root = loader.load();
//
//            // Create a new scene for the home page
//            Scene homepageScene = new Scene(root);
//
//            // Get the Stage from the current Node (you can adjust this if needed)
//            Stage currentStage = (Stage) verify.getScene().getWindow();
//
//            // Set the home page scene on the stage
//            currentStage.setScene(homepageScene);
//            currentStage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // }
//    }


}
