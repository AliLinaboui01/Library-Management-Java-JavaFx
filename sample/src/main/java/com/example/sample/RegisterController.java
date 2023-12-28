package com.example.sample;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import BD.DataBase;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.Properties;
import java.util.Random;

public class RegisterController {
    @FXML
    private TextField cne;
    @FXML
    private TextField email;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private PasswordField password;
    @FXML
    private Button uploadImageButton;
    private String imagePath;
    @FXML
    private TextField userName;
    private final SendMail sendMail = new SendMail("alilinaboui@gmail.com", "gpmo chhj lapm uzwf");
    public void register(ActionEvent e) {
        String mailUser = email.getText();
        String userCne = cne.getText();
        String fName = firstName.getText();
        String lName = lastName.getText();
        String userN = userName.getText();
        String pass = password.getText();
        String userType ="student";
        String verificationCode = generateVerificationCode();
            try {
                String emailPattern = "^[a-zA-Z]+\\.[a-zA-Z]+@etu\\.uae\\.ac\\.ma$";
                if(isEmailUnique(mailUser)&&handelRegisterInformation(userCne,fName,lName,userN,pass)&& mailUser.matches(emailPattern)&&!imagePath.isEmpty()){
                    DataBase dataBase = new DataBase();
                    Connection connection = dataBase.connect();
                    Statement statement = connection.createStatement();
                    String query = "INSERT INTO users (Cne,email,firstName,image,lastName,password,username,userType) VALUES (?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement= connection.prepareStatement(query);
                    preparedStatement.setString(1,userCne);
                    preparedStatement.setString(2,mailUser);
                    preparedStatement.setString(3,fName);
                    preparedStatement.setString(4,imagePath);
                    preparedStatement.setString(5,lName);
                    preparedStatement.setString(6,pass);
                    preparedStatement.setString(7,userN);
                    preparedStatement.setString(8,userType);
                    preparedStatement.executeUpdate();
                    sendMail.SendVerificationMail(mailUser, verificationCode);//send email
                    goToVerify(verificationCode,e);//go to verify otp interface
                    System.out.println("Verification email sent successfully.");
                }else {
                    errorAlert(e);
                }
            } catch (MessagingException ex) {
                ex.printStackTrace();
                System.out.println("Error sending verification email.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }

    }

    public void goLogin(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            currentStage.setScene(nextScene);
            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private String generateVerificationCode() {
        // Generate a random 6-digit verification code
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
    public boolean handelRegisterInformation(String fName,String lName ,String pass , String userName , String cne){
        return !fName.trim().isEmpty()&&!lName.trim().isEmpty()&&!pass.trim().isEmpty()&&!userName.trim().isEmpty()&&!cne.trim().isEmpty();
    }
    public void uploadImage(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        Stage stage = (Stage) uploadImageButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                String imageDirectory = "src/main/resources/com/example/sample/imgs/users/profile";
                String originalFileName = selectedFile.getName();
                String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
                imagePath ="imgs/users/profile/"+uniqueFileName;
                Path destinationPath = Paths.get(System.getProperty("user.dir"), imageDirectory, uniqueFileName);
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Image uploaded successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void errorAlert(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("errorAlert.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(nextScene);
            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void goToVerify(String verificationCode,ActionEvent e) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registerVerification.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            RegisterVerificationController registerVerificationController = loader.getController();
            if (registerVerificationController != null) {
                registerVerificationController.setVerifyCode(verificationCode);
            } else {
                System.out.println("Error: RegisterVerificationController is null");
            }
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            currentStage.setScene(nextScene);
            currentStage.show();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    private boolean isEmailUnique(String email) {
        try {
            DataBase dataBase = new DataBase();
            Connection connection = dataBase.connect();
            String query = "SELECT COUNT(*) FROM users WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count == 0; // If count is 0, the email is unique
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return false; // Default to false if an exception occurs
    }

}
