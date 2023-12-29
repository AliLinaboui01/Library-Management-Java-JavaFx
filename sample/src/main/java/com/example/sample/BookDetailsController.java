package com.example.sample;

import BD.DataBase;
import Session.SessionManager;
import com.google.zxing.WriterException;
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
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Book;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

public class BookDetailsController implements Initializable {
    @FXML
    private Button borrowButton;


    @FXML
    private Label BookAuthor1;
    @FXML
    private Label BookAuthor11;
    @FXML
    private Label BookName;

    @FXML
    private Label Bookidpass;

    @FXML
    private Label Language;
    @FXML
    private ImageView BookImage;


    @FXML
    private Label category;

    @FXML
    private Label description;

    @FXML
    private Label pages;

    @FXML
    private Label rating;

    @FXML
    private Label status;

    public int BookId;

    @FXML
    private Hyperlink link;
    @FXML
    private Button username;
    @FXML
    private Label dayTime;


    @FXML
    private Label timeAm;

    @FXML
    private Hyperlink link2;
    public void setBookId(int id) {
        this.BookId = id;
    }
    public int getBookId() {
        return this.BookId;
    }
    public BookDetailsController() {}
    public BookDetailsController(int id) {
            this.BookId = id;
    }

    public void borrow(ActionEvent event) {
        try {
            // Load the FXML file for the reservation
            FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
            Parent root = loader.load();
            int idBook = getBookId();
            int idUser = 1;
            ReservationController reservationController = loader.getController();


            // Call the setIds method in the ReservationController
            reservationController.setIds(idBook, idUser);
            // Create a new dialog
            Dialog<Void> dialog = new Dialog<>();
            dialog.initStyle(StageStyle.UNDECORATED);

            // Set the content of the dialog to the loaded FXML
            dialog.getDialogPane().setContent(root);

            // Get the Stage from the current Node
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the owner of the dialog to the current stage
            dialog.initOwner(currentStage);
            // Show the dialog
            dialog.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        } catch (WriterException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(getBookId());
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateLabels())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        DataBase dataBase = new DataBase();
        Connection connection = dataBase.connect();
        String currentUser = SessionManager.getCurrentUser();
        if (currentUser != null) {
            username.setText(currentUser);
        } else {
            // Handle the case where the current user is not set
            username.setText("faild");
        }
        String query = "SELECT * FROM Books WHERE bookID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, getBookId());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Book book = new Book();
                    book.setName(resultSet.getString("title"));
                    book.setImageSrc(resultSet.getString("image"));
                    Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
                    BookImage.setImage(image);
                    book.setAuthor(resultSet.getString("author"));
                    book.setId(resultSet.getInt("bookID"));
                    BookAuthor1.setText(resultSet.getString("author"));
                    BookAuthor11.setText(resultSet.getString("author"));
                    BookName.setText(book.getName());
                    Language.setText(resultSet.getString("Language"));
                    category.setText(resultSet.getString("category"));
                    description.setText(resultSet.getString("description"));
                    pages.setText(String.valueOf(resultSet.getInt("pages")));
                    rating.setText(String.valueOf(resultSet.getFloat("rating")));
                    int quanity = resultSet.getInt("availableQuantity");
                    if (quanity > 2) {
                        status.setText("available in Library");
                    }else{
                        status.setText("not available");
                    }



                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dataBase.closeConnection();
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

    @FXML
    void goToHome(ActionEvent e) {
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
}
