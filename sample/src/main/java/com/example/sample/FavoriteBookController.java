package com.example.sample;

import BD.DataBase;
import Session.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class FavoriteBookController implements Initializable {
    @FXML
    private VBox favoriteBookVbox;
    private List<Book> favoriteBooks;
    @FXML
    private Button username;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String currentUser = SessionManager.getCurrentUser();
        username.setText(currentUser);
        favoriteBooks = new ArrayList<>(favoriteBooks());
        for (Book book : favoriteBooks) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("cardBook.fxml"));
            try {
                HBox cardBox = fxmlLoader.load();
                CardBookController cardBookController =fxmlLoader.getController();
                cardBookController.setData(book);
                favoriteBookVbox.getChildren().add(cardBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    private List<Book> favoriteBooks(){
        List<Book> ls = new ArrayList<>();

        DataBase dataBase=new DataBase();
        Connection conn = dataBase.connect();
        String select = "SELECT books.author, books.availableQuantity, books.bookID, books.category, books.description, books.image, books.isbn, books.language, books.pages, books.quantity, books.rating, books.title FROM books INNER JOIN favoritebooks ON books.bookID = favoritebooks.idBook INNER JOIN users ON favoritebooks.idUser = users.userID where idUser="+SessionManager.getCurrentUserId();
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
    public void goToHome(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);
            currentStage.show();
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
    public void goToProfile(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
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
    public void goToMyShelf(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookborrowd.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);
            currentStage.show();
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
    public void goToBooks(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allBooks.fxml"));
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

}
