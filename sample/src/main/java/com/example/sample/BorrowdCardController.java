package com.example.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Book;

import java.io.IOException;
import java.util.Objects;

public class BorrowdCardController {

    @FXML
    private Label BookAuthor;

    @FXML
    private ImageView BookImg;

    @FXML
    private Label BookRating;

    @FXML
    private Label BookTitle;
    public int idBook ;
    public void setData(Book book) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        BookImg.setImage(image);
        idBook = book.getIdBook();
        BookTitle.setText(book.getName());
        BookAuthor.setText(book.getAuthor());
//        BookRating.setText(book.getRating());
    }

    public void goToBookDetails(ActionEvent event ) {
        System.out.println(idBook);
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookDetails.fxml"));

            BookDetailsController bookDetailsController = new BookDetailsController(idBook);

            // Set the controller instance in the FXMLLoader
            loader.setController(bookDetailsController);
            Parent root = loader.load();
            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);

            /*

            Parent root = loader.load();

            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene on the stage
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);


             */

            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }

}
