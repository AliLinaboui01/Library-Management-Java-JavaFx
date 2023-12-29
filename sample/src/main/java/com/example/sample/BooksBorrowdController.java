package com.example.sample;

import BD.DataBase;
import Session.SessionManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Book;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BooksBorrowdController implements Initializable {
    @FXML
    private GridPane gridborrowdbook;
    @FXML
    private Label dayTime;



    @FXML
    private Label timeAm;

    @FXML
    private Button username;

    private List<Book> borrowedBooks;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int idUser=SessionManager.getCurrentUserId();
        String currentUser = SessionManager.getCurrentUser();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateLabels())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        if (currentUser != null) {
            username.setText(currentUser);
        } else {
            // Handle the case where the current user is not set
            username.setText("user x");
        }
        borrowedBooks = getReservedBooksByUserId(idUser);



        int column = 0;
        int row = 1;

        for (Book book : borrowedBooks) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("borrowdCard.fxml"));
            VBox bookBox;
            try {
                bookBox = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            BorrowdCardController borrowdCardController = fxmlLoader.getController();
            borrowdCardController.setData(book);


            // Check if we need to move to the next row
            if (column == 3) {
                column = 0;
                ++row;
            }

            gridborrowdbook.add(bookBox, column++, row);
            GridPane.setMargin(bookBox, new Insets(15));

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
    private List<Book> getBorrowedBooks() {
        List<Book> ls = new ArrayList<>();
        Book book = new Book();



        book = new Book();
        book.setName("RICH DAD POOR DAD");
        book.setImageSrc("imgs/RICHEST.jpeg");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);

        book = new Book();
        book.setName("THW WARREN BUFFETT");
        book.setImageSrc("imgs/RICHEST.jpeg");
        book.setAuthor("Robert G. Hagstrom");
        ls.add(book);
        book = new Book();
        book.setName("RICH DAD POOR DAD");
        book.setImageSrc("imgs/RICHEST.jpeg");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);

        book = new Book();
        book.setName("RICH DAD POOR DAD");
        book.setImageSrc("imgs/RICHEST.jpeg");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);


        book = new Book();
        book.setName("RICH DAD POOR DAD");
        book.setImageSrc("imgs/RICHEST.jpeg");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);

        return ls;
    }


    public List<Book> getReservedBooksByUserId(int userId) {
        List<Book> reservedBooks = new ArrayList<>();

        DataBase dataBase = new DataBase();
        try (Connection connection = dataBase.connect()) {
            String sql = "SELECT r.`reservationID`, r.`bookID`, r.`qrCode`, r.`reservationDate`, r.`returnDate`, r.`userID`, r.`purpos`, " +
                    "b.`author`, b.`availableQuantity`, b.`category`, b.`description`, b.`image`, b.`isbn`, b.`language`, b.`pages`, " +
                    "b.`quantity`, b.`rating`, b.`title`, b.`createdat`, b.`hyperlinkweb` " +
                    "FROM `reservations` r " +
                    "INNER JOIN `books` b ON r.`bookID` = b.`bookID` " +
                    "WHERE r.`userID` = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Book reservedBook = new Book();


                        reservedBook.setAuthor(resultSet.getString("author"));

                        reservedBook.setImageSrc(resultSet.getString("image"));
                        reservedBook.setName(resultSet.getString("title"));
                         //reservedBook.setId(resultSet.getInt("bookID"));
                        System.out.println(resultSet.getInt("bookID"));
                        reservedBook.setIdBook(resultSet.getInt("bookID"));

                        reservedBooks.add(reservedBook);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's requirements
        }

        return reservedBooks;
    }
    public void goToHome(ActionEvent e) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

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
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);

            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }

    }

    public void goToFavoritesBook(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("favoriteBook.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
            currentStage.close();
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
