package com.example.sample;

import BD.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AllBooksController implements Initializable {
    @FXML
    private VBox allBoxVbox;
    private List<Book> allBooks;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    private List<Book> allBooks(){
        List<Book> ls = new ArrayList<>();
        Book book = new Book();





        book = new Book();
        book.setName("RICH DAD POOR DAD");
        book.setImageSrc("imgs/téléchargement (1).png");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);

        book = new Book();
        book.setName("THW WARREN BUFFETT");
        book.setImageSrc("imgs/téléchargement (1).png");
        book.setAuthor("Robert G. Hagstrom");
        ls.add(book);
        book = new Book();
        book.setName("RICH DAD POOR DAD");
        book.setImageSrc("imgs/téléchargement (1).png");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);




        return ls;
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
}
