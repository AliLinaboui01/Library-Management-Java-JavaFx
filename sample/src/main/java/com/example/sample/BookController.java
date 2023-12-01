package com.example.sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Book;

import java.util.Objects;

public class BookController {
    @FXML
    private Label authorName;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookName;
    public void setData(Book book){
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        bookImage.setImage(image);
        bookName.setText(book.getName());
        authorName.setText(book.getAuthor());
    }

}
