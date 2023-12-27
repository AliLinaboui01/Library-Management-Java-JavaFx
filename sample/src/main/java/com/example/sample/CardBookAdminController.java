package com.example.sample;

import BD.DataBase;
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
import javafx.stage.Stage;
import model.Book;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class CardBookAdminController {
    @FXML
    private Label authorName;

    @FXML
    private Label bookCategory;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookQuantity;

    @FXML
    private Label bookTitle;

    @FXML
    private Button editButton;

    @FXML
    private Button removeButton;
    private Book myBook;
    private  int bookID;
    public CardBookAdminController(){}
    public void setData(Book book){

        bookTitle.setText(book.getName());
        authorName.setText(book.getAuthor());
        this.myBook=book;
        this.bookID=book.getIdBook();
        String imageUrl = book.getImageSrc();
        if (imageUrl == null || imageUrl.isEmpty()) {
            bookImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/rich.jpeg"))));
        } else {
            try {
                // Load user's image
                InputStream stream = getClass().getResourceAsStream(imageUrl);
                if (stream != null) {
                    bookImage.setImage(new Image(stream));
                } else {
                    // Provide a default image if the stream is null or invalid
                    bookImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/rich.jpeg"))));
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception (e.g., log an error message)
                // Provide a default image in case of an exception
                bookImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/rich.jpeg"))));
            }
        }
    }
    public void updateBook(ActionEvent event){
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editBookAdmin.fxml"));

            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);
            EditBookAdminController editBookAdminController = loader.getController();
            editBookAdminController.setBookInformation(this.myBook);
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
    public void removeBook(ActionEvent event){
        DataBase dataBase = new DataBase();
        Connection connection = dataBase.connect();
        try{

            Statement statement = connection.createStatement();
            String query = "DELETE FROM books WHERE bookID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,bookID);
            preparedStatement.executeUpdate();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allBooksAdmin.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene on the stage
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);

            currentStage.show();
        }catch(SQLException | IOException e){
            e.printStackTrace();
        }
    }
}
