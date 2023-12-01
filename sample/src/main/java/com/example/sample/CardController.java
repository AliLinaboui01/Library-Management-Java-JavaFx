package com.example.sample;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Book;

import java.util.Objects;

public class CardController {
    @FXML
    private Label authorName;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookName;
    private String[] colors = {"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};
    @FXML
    private HBox box;

    public void setData(Book book){
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        bookImage.setImage(image);
        authorName.setText(book.getAuthor());
        bookName.setText(book.getName());
        box.setStyle("-fx-background-color: #" + colors[(int)(Math.random()*colors.length)] +
                "; -fx-background-radius: 15; -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.1) , 10 , 0 , 0 , 10 )");
    }
}
