package com.example.sample;

import BD.DataBase;
import Session.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CardBookController {

    @FXML
    private Label authorName;
    @FXML
    private ImageView imageBook;
    @FXML
    private ImageView imageLove;
    @FXML
    private Label possibility;
    @FXML
    private Label titleBook;

    @FXML
    private Label categoryId;
    @FXML
    private Label categoryName;
    private int isLoved=-1 ;
    private boolean isBookFavorite = false;
    private int idBook;
    public void setData(Book book) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        imageBook.setImage(image);
        titleBook.setText(book.getName());
        authorName.setText(book.getAuthor());
        categoryName.setText(book.getCategory());
        this.idBook=book.getIdBook();
        if(isBookAlreadyLoved(SessionManager.getCurrentUserId(),this.idBook)){
            Image redLove = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/love.png")));
            imageLove.setImage(redLove);
        }
    }
    public void getBookDetails(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookDetails.fxml"));
            BookDetailsController bookDetailsController = new BookDetailsController(this.idBook);

            // Set the controller instance in the FXMLLoader
            loader.setController(bookDetailsController);
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
    public void loveBook(ActionEvent e){
        Book book = new Book();
        book.setFavorite(!isBookFavorite);
        Image redLove = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/love.png")));
        Image love = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/love!.png")));
        if (book.getFavorite()){
            insertLovedBookIntoDatabase(SessionManager.getCurrentUserId(),this.idBook);
            imageLove.setImage(redLove);
            this.isLoved=1;
        }else{
            removeLovedBookFromDatabase(SessionManager.getCurrentUserId(),this.idBook);
            imageLove.setImage(love);
        }
        isBookFavorite = book.getFavorite();
    }
    public void insertLovedBookIntoDatabase(int userId,int bookId) {
        if (!isBookAlreadyLoved(userId, bookId)) {
            DataBase dataBase = new DataBase();
            try (Connection connection = dataBase.connect()) {
                String query = "INSERT INTO favoritebooks (idBook, idUser) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, bookId);
                    preparedStatement.setInt(2, userId);
                    preparedStatement.executeUpdate();
                }
                System.out.println("Book loved and inserted into the database successfully.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("User has already loved this book.");
        }
    }
    public void removeLovedBookFromDatabase(int userId,int bookId) {
        try {
            DataBase dataBase = new DataBase();
            Connection connection = dataBase.connect();

            String query = "DELETE FROM favoritebooks WHERE idBook = ? AND idUser = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, bookId);
                preparedStatement.setInt(2, userId);
                preparedStatement.executeUpdate();
            }

            System.out.println("Book removed from favorites in the database successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
    private boolean isBookAlreadyLoved(int userId, int bookId) {
        System.out.println("DEBUG: userId=" + userId + ", bookId=" + bookId);
        DataBase dataBase = new DataBase();
        try (Connection connection = dataBase.connect()) {
            String query = "SELECT 1 FROM favoritebooks WHERE idBook = ? AND idUser = ? LIMIT 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, bookId);
                preparedStatement.setInt(2, userId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // Returns true if there is at least one record
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
