package com.example.sample;

import BD.DataBase;
import Session.SessionManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.User;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private ImageView UserImage;
    @FXML
    private Label dayTime;

   String imagePath = "imgs/831.jpg" ;
    @FXML
    private Label timeAm;
    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Label nomberContribution;

    @FXML
    private Label nomberReservation;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    @FXML
    private Button username;

    @FXML
    private Button editImageUser;


    public void goToHome(ActionEvent e) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            //currentStage.setFullScreen(true);

            // Set the new scene on the stage
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);
            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }

    public void goToAllBooks(ActionEvent event) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allBooks.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene on the stage
            currentStage.setFullScreen(true);

            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);


        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }

    }
    public void goToMyshelf(ActionEvent event) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookborrowd.fxml"));
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
    public void gotoProfile(ActionEvent event) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene on the stage
            currentStage.setFullScreen(true);

            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);


        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }

    @FXML
    void goToContribute(ActionEvent event) {
    HomeController homeController = new HomeController();
    homeController.goToContribute(event);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //time
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateLabels())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        //
        String currentUser = SessionManager.getCurrentUser();
        int idUser = SessionManager.getCurrentUserId();
        System.out.println(idUser);
        if (currentUser != null) {
            username.setText(currentUser);
        }
        int numberContibution = getNumberOfContributionsByUserId(idUser);
        nomberContribution.setText(""+numberContibution);

        int numberReservation = getNumberOfReservationsByUserId(idUser);
        nomberReservation.setText(""+numberReservation);
        User user = new User();

        user = getUserById(idUser);

      email.setText(user.getEmail());

       firstName.setText(user.getFirstName());

       lastName.setText(user.getLastName());

       password.setText(user.getPassword());
     userName.setText(user.getUsername());
        if (user.getImage() == null){
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/icons8-approval-48.png")));
            UserImage.setImage(image);

        }else{
            String imageUrl = user.getImage();
            if (imageUrl == null || imageUrl.isEmpty()) {
                UserImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/831.jpg"))));
            } else {
                try {
                    // Load user's image
                    InputStream stream = getClass().getResourceAsStream(imageUrl);
                    if (stream != null) {
                        UserImage.setImage(new Image(stream));
                    } else {
                        // Provide a default image if the stream is null or invalid
                        UserImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/831.jpg"))));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle the exception (e.g., log an error message)
                    // Provide a default image in case of an exception
                    UserImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/831.jpg"))));
                }
            }
        }




    }
    private void updateLabels() {
        // Get the current date and time
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        // Format the date as "dd-MMM-yyyy"
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        String formattedDate = currentDate.format(dateFormatter);

        // Format the time as "hh:mm a"
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = currentTime.format(timeFormatter);

        // Update the labels
        dayTime.setText(formattedDate);
        timeAm.setText(formattedTime);
    }

    public User getUserById(int userId) {
        User user = null;
        DataBase dataBase = new DataBase();
        try (Connection connection =dataBase.connect()) {
            String sql = "SELECT `Cne`, `email`, `firstName`, `image`, `lastName`, `password`, `userID`, `username`, `userType`, `createdDate` " +
                    "FROM `users` WHERE `userID` = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        user = new User();
                        user.setCne(resultSet.getString("Cne"));
                        user.setEmail(resultSet.getString("email"));
                        user.setFirstName(resultSet.getString("firstName"));
                        user.setImage(resultSet.getString("image"));
                        user.setLastName(resultSet.getString("lastName"));
                        user.setPassword(resultSet.getString("password"));
                        user.setUserID(resultSet.getInt("userID"));
                        user.setUsername(resultSet.getString("username"));
                        user.setUserType(resultSet.getString("userType"));
                        user.setCreatedDate(resultSet.getDate("createdDate"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's requirements
        }

        return user;
    }

    @FXML
    void updatProfile(ActionEvent event) {
        try {
            updateUserById(SessionManager.getCurrentUserId(),email.getText(),firstName.getText(),lastName.getText(),imagePath,userName.getText(), password.getText());
            HomeController homeController = new HomeController();
            homeController.goToHome(event);
        }catch (Exception exception) {
            System.out.println("error in update user");
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
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Image replaced successfully!"+imagePath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                // Handle the exception (e.g., show an error message)
            }
        }
        if(selectedFile!=null) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/icons8-approval-48.png")));
            UserImage.setImage(image);
        }

    }
    public void updateUserById(int userId, String email, String firstName, String lastName, String image, String username, String password) {
        DataBase dataBase = new DataBase();
        try (Connection connection =dataBase.connect()) {
            String sql = "UPDATE `users` SET " +
                    "`email`=?, " +
                    "`firstName`=?, " +
                    "`lastName`=?, " +
                    "`image`=?, " +
                    "`username`=?, " +
                    "`password`=? " +
                    "WHERE `userID`=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email != null ? email : "");
                preparedStatement.setString(2, firstName != null ? firstName : "");
                preparedStatement.setString(3, lastName != null ? lastName : "");
                preparedStatement.setString(4, image != null ? image : "");
                preparedStatement.setString(5, username != null ? username : "");
                preparedStatement.setString(6, password != null ? password : "");
                preparedStatement.setInt(7, userId);


                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's requirements
        }
    }
    public int getNumberOfContributionsByUserId(int userId) {
        int numberOfContributions = 0;

        DataBase dataBase = new DataBase();
        try (Connection connection = dataBase.connect()) {
            String sql = "SELECT COUNT(*) FROM `contributbook` WHERE `userID` = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        numberOfContributions = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's requirements
        }

        return numberOfContributions;
    }
    public int getNumberOfReservationsByUserId(int userId) {
        int numberOfReservations = 0;

        DataBase dataBase = new DataBase();
        try (Connection connection = dataBase.connect()) {
            String sql = "SELECT COUNT(*) FROM `reservations` WHERE `userID` = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        numberOfReservations = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's requirements
        }

        return numberOfReservations;
    }
}
