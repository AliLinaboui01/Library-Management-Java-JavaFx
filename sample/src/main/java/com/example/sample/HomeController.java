package com.example.sample;


import Session.SessionManager;

import BD.DataBase;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private HBox bookCompenenet;
    @FXML
    private HBox recomondebooks;
    @FXML
    private Button username;
    @FXML
    private HBox recomondebooks2;
    @FXML
    private HBox acadimicbook;
    private List<Book> recentlyAdded;
    private  List<Book> recomdedforyou;
    private  List<Book> acadimicbooks;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recentlyAdded = new ArrayList<>(recentlyAdded());
        recomdedforyou = new ArrayList<>(recomdedforyou());

        acadimicbooks = new ArrayList<>(recomdedforyou());
        String currentUser = SessionManager.getCurrentUser();
        if (currentUser != null) {
            username.setText(currentUser);
        } else {
            // Handle the case where the current user is not set
            username.setText("faild");
        }

        acadimicbooks = new ArrayList<>(getAcadimicbooks());

        for (Book book : recentlyAdded) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("bookcompnent.fxml"));
            try {
                VBox cardBox = fxmlLoader.load();
                BookcompenentController bookcompenentController =fxmlLoader.getController();
                bookcompenentController.setData(book);
                bookCompenenet.getChildren().add(cardBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        for (Book book : recomdedforyou) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("bookItem.fxml"));
            try {
                VBox cardBox = fxmlLoader.load();
                BookItemController bookItemController =fxmlLoader.getController();
                bookItemController.setData(book);
                recomondebooks.getChildren().add(cardBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
       for (Book book :acadimicbooks) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("bookItem.fxml"));
            try {
                VBox bookbox = fxmlLoader.load();
                BookItemController bookItemController =fxmlLoader.getController();
                bookItemController.setData(book);
                acadimicbook.getChildren().add(bookbox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }



    private List<Book> recentlyAdded() {
        DataBase dataBase = new DataBase();
        Connection connection = dataBase.connect();
        List<Book> books = new ArrayList<>();
        String query="SELECT * FROM Books  WHERE availableQuantity > 1 ORDER BY bookID DESC ";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setName(resultSet.getString("title"));
                book.setImageSrc(resultSet.getString("image"));
                book.setAuthor(resultSet.getString("author"));
                book.setId(resultSet.getInt("bookID"));
                books.add(book);
            }
            dataBase.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }


    private List<Book>recomdedforyou() {
        DataBase dataBase = new DataBase();
        Connection connection = dataBase.connect();
        List<Book> books = new ArrayList<>();
        String query="SELECT * FROM Books WHERE availableQuantity > 1 ORDER BY bookID DESC  ";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setName(resultSet.getString("title"));
                book.setImageSrc(resultSet.getString("image"));
                book.setAuthor(resultSet.getString("author"));
                book.setId(resultSet.getInt("bookID"));
                books.add(book);
            }
            dataBase.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }


    private List<Book>getAcadimicbooks() {
        DataBase dataBase = new DataBase();
        Connection connection = dataBase.connect();
        List<Book> books = new ArrayList<>();
        String query="SELECT * FROM Books   WHERE category = 'acadimic' AND  availableQuantity > 1 ORDER BY bookID DESC";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setName(resultSet.getString("title"));
                book.setImageSrc(resultSet.getString("image"));
                book.setAuthor(resultSet.getString("author"));
                book.setId(resultSet.getInt("bookID"));
                books.add(book);
            }
            dataBase.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
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
}
