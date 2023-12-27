package com.example.sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Book;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BookItemController implements Initializable {
    @FXML
    private ImageView imgbook;
    @FXML
    private Label authorbook;
    @FXML
    private Label ratingbook;

    @FXML
    private Label titlebook;
    private int id_of_book ;

    public int getId() {
        return this.id_of_book;
    }
    public void setBookId(int id) {
        this.id_of_book = id;
    }
    public void setData(Book book) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        imgbook.setImage(image);
        titlebook.setText(book.getName());
        authorbook.setText(book.getAuthor());
        this.setBookId(book.getId());
        System.out.println(id_of_book);
    }
    public void goTobookDetails(ActionEvent event ) {
        System.out.println(this.getId());
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookDetails.fxml"));

            BookDetailsController bookDetailsController = new BookDetailsController(this.getId());

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
