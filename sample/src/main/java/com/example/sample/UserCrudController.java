package com.example.sample;

import BD.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserCrudController implements Initializable {


    @FXML
    private Button editImageUser;

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
    @FXML
    private ImageView validateImage;

    private String imagePath;

    ActionEvent Initialevent ;

    @FXML
    void AddUser(ActionEvent event) {
       // UploadImage();
        String sql = "INSERT INTO Users (Cne, email, firstName, lastName, password, userType,username,image) VALUES (?, ?, ?, ?, ?, ?,?,?)";

        try {
            DataBase dataBase = new DataBase();
            Connection conn = dataBase.connect();


            if (UserCne.getText() != null && !UserCne.getText().trim().isEmpty() &&
                    UserEmail.getText() != null && !UserEmail.getText().trim().isEmpty() &&
                    UserFirstName.getText() != null && !UserFirstName.getText().trim().isEmpty() &&
                    UserLastName.getText() != null && !UserLastName.getText().trim().isEmpty() &&
                    UserName.getText() != null && !UserName.getText().trim().isEmpty()){

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


                        dataBase.closeConnection();
               successAlert(event);
                goToPageUser(event);


            }else{
                dangerAlert(event);
            }
        } catch (Exception exception) {
            System.out.println("NO DATA FOUND");
        }



    }
    @FXML
    private void goToAlluser(ActionEvent event) {
        try {
            Initialevent = event;
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allusers.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
          //  Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene on the stage
            Stage currentStage =new Stage();

            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);

            currentStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage()); // Handle the exception appropriately
        }
    }
    @FXML
    private void goToPageUser(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("allusers.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add User");
            stage.setScene(new Scene(root, 900, 700));

            // You can pass any necessary data to the controller of the new FXML if needed
          //  AllUsersController formAddUserController = fxmlLoader.getController();
            // formAddUserController.setSomeData(someData);
            stage.setFullScreen(true);

            stage.show();

            // Optionally, you can close the current stage if needed
            // ((Stage) someNode.getScene().getWindow()).close();
        } catch (IOException e) {
            System.out.println(e.getMessage()); // Handle the exception
        }

    }
    @FXML
    private void UploadImage()   {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        Stage stage = (Stage) editImageUser.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                // Get the directory to save images outside the project (adjust as needed)
                String imageDirectory = "src/main/resources/com/example/sample/imgs/users/profile";

                // Generate a unique file name based on the original file name
                String originalFileName = selectedFile.getName();
                String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
                imagePath =  "imgs/users/profile/" + uniqueFileName;
                // Create a new file in the destination directory
               Path destinationPath = Paths.get(System.getProperty("user.dir"), imageDirectory, uniqueFileName);
                // Copy the selected file to the destination directory
                Files.move(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Image replaced successfully!");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                // Handle the exception (e.g., show an error message)
            }
        }
        if(selectedFile!=null) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/icons8-approval-48.png")));
            validateImage.setImage(image);
        }

    }





    public void successAlert(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("successAlert.fxml"));
        Parent root = loader.load();
        Dialog<Void> dialog = new Dialog<>();
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.getDialogPane().setContent(root);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        dialog.initOwner(currentStage);
        dialog.showAndWait();
    }


    public void dangerAlert(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dangerAlert.fxml"));
        Parent root = loader.load();
        Dialog<Void> dialog = new Dialog<>();
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.getDialogPane().setContent(root);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        dialog.initOwner(currentStage);
        dialog.showAndWait();
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {

    }
}
