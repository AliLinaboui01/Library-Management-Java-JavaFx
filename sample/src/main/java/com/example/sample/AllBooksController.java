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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Book;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AllBooksController implements Initializable {
    @FXML
    private VBox allBoxVbox;
    @FXML
    private Button username;
    @FXML
    private Label dayTime;


    @FXML
    private Label timeAm;
    private List<Book> allBooks;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //time
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateLabels())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        String currentUser = SessionManager.getCurrentUser();
        username.setText(currentUser);
        allBooks = new ArrayList<>(allBooks());
        for (Book book : allBooks) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("cardBook.fxml"));
            try {
                HBox cardBox = fxmlLoader.load();
                CardBookController cardBookController =fxmlLoader.getController();
                cardBookController.setData(book);
                allBoxVbox.getChildren().add(cardBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
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
    private List<Book> allBooks(){
        List<Book> ls = new ArrayList<>();

        DataBase dataBase=new DataBase();
        Connection conn = dataBase.connect();
        String select = "SELECT * FROM books";
        try{

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            while(resultSet.next()){
                Book book = new Book();
                book.setAuthor(resultSet.getString("author"));
                book.setAvailableQuantity(resultSet.getInt("availableQuantity"));
                book.setIdBook(resultSet.getInt("bookID"));
                book.setCategory(resultSet.getString("category"));
                book.setDescription(resultSet.getString("description"));
                book.setImageSrc(resultSet.getString("image"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setLanguage(resultSet.getString("language"));
                book.setPages(resultSet.getInt("pages"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setRating(resultSet.getFloat("rating"));
                book.setName(resultSet.getString("title"));
                ls.add(book);
            }
            dataBase.closeConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }




    public void goToHome(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
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

    @FXML
    void goToContribute(ActionEvent event) {
      HomeController homeController = new HomeController();
      homeController.goToContribute(event);
    }
    @FXML
    void gotoProfile(ActionEvent event) {
      HomeController homeController = new HomeController();
      homeController.gotoProfile(event);
    }

}
