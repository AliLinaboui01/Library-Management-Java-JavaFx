package com.example.sample;

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
    private Button previewButton;

    @FXML
    private Label titleBook;
    @FXML
    private Label categoryId;

    @FXML
    private Label categoryName;
    private boolean isBookFavorite = false;
    public void setData(Book book) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        imageBook.setImage(image);
        titleBook.setText(book.getName());
        authorName.setText(book.getAuthor());
    }
    public void getBookDetails(ActionEvent e){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookDetails.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

            // Set the new scene on the stage
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
            imageLove.setImage(redLove);
            // insert in favorite table
        }else{
            imageLove.setImage(love);
        }
        isBookFavorite = book.getFavorite();
    }
}
