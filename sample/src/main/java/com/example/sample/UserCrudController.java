package com.example.sample;

import BD.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;

public class UserCrudController {


    @FXML
    private Button UploadImageUser;

    @FXML
    private TextField UserCne;

    @FXML
    private TextField UserEmail;

    @FXML
    private TextField UserFirstName;

    @FXML
    private TextField UserLastName;

    @FXML
    private TextField UserPassword;

    @FXML
    private TextField UserName;


    @FXML
    private ToggleGroup role;

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private FileInputStream fis;
    private String imagePath;
    private ImageView imageUser;

    @FXML
    void addNewUser(ActionEvent event) {


       // UploadImage();
        String sql = "INSERT INTO Users (Cne, email, firstName, lastName, password, userType,username,image) VALUES (?, ?, ?, ?, ?, ?,?,?)";

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement st = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, UserCne.getText());
            preparedStatement.setString(2, UserEmail.getText());
            preparedStatement.setString(3, UserFirstName.getText());
            preparedStatement.setString(4, UserLastName.getText());
            preparedStatement.setString(5, UserPassword.getText());

            String selectedRole = ((RadioButton) role.getSelectedToggle()).getText();
            preparedStatement.setString(6, selectedRole);
            preparedStatement.setString(7, UserName.getText());
            preparedStatement.setString(8, imagePath);


            preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Data inserted into the database successfully!");
            alert.showAndWait();
            loadAllUserScene(event);

        } catch (Exception exception) {

        }


    }
    private void loadAllUserScene(ActionEvent event) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allusers.fxml"));
            Parent root = loader.load();

            // Create a new scene
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
    }
    @FXML
    private void UploadImage()   {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        Stage stage = (Stage) UploadImageUser.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                // Get the directory to save images outside the project (adjust as needed)
                String imageDirectory = "src/main/resources/com/example/sample/imgs/users/profile";

                // Generate a unique file name based on the original file name
                String originalFileName = selectedFile.getName();
                String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
                imagePath =  uniqueFileName;
                // Create a new file in the destination directory
               Path destinationPath = Paths.get(System.getProperty("user.dir"), imageDirectory, uniqueFileName);
                // Copy the selected file to the destination directory
                Files.move(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Image replaced successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception (e.g., show an error message)
            }
        }

    }


    @FXML
    private void goToPageUser(ActionEvent event) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allusers.fxml"));
            Parent root = loader.load();

            // Create a new scene
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
    }

}
