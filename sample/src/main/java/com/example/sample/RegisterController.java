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
    private final SendMail sendMail = new SendMail("alilinaboui@gmail.com", "gpmo chhj lapm uzwf");
    public void register(ActionEvent e) {
        String mailUser = email.getText();

        // Define the regular expression for the email pattern
        String emailPattern = "^[a-zA-Z]+\\.[a-zA-Z]+@etu\\.uae\\.ac\\.ma$";

        // Check if the entered email matches the pattern
        if (mailUser.matches(emailPattern)) {
            String verificationCode = generateVerificationCode();

            try {
                sendMail.SendVerificationMail(mailUser, verificationCode);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("registerVerification.fxml"));
//                loader.setController(new RegisterVerificationController(verificationCode));
                Parent root = loader.load();

                // Create a new scene
                Scene nextScene = new Scene(root);

                // Get the Stage from the current Node (you can adjust this if needed)
                Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                // Set the new scene on the stage
                currentStage.setScene(nextScene);
                currentStage.show();

                System.out.println("Verification email sent successfully.");
            } catch (MessagingException ex) {
                ex.printStackTrace();
                System.out.println("Error sending verification email.");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            System.out.println("Invalid email format. Please enter a valid email address.");
            // You might want to display an error message to the user.
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
