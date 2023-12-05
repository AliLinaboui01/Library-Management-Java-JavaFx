package com.example.sample;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import SMTP_Mail.SendMail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class RegisterController {

    @FXML
    private PasswordField confPassword;

    @FXML
    private TextField email;

    @FXML
    private TextField name;
    @FXML
    private Button loginLabel;

    @FXML
    private PasswordField password;

    @FXML
    private Button registerButton;
    private final SendMail sendMail = new SendMail("alilinaboui@gmail.com", "eyoc hrcz jumz crbg");
    public void register(ActionEvent e) {

        String mailUser = email.getText();

        String verificationCode = generateVerificationCode();
//        System.out.println(verificationCode);
        try {
            sendMail.SendVerificationMail(mailUser, verificationCode);
            System.out.println("Verification email sent successfully.");
        } catch (MessagingException ex) {
            ex.printStackTrace();
            System.out.println("Error sending verification email.");
        }
    }
    public void goLogin(ActionEvent e){
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
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
    private String generateVerificationCode() {
        // Generate a random 6-digit verification code
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
