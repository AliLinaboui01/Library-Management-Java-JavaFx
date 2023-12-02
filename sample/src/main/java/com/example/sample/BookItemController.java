package com.example.sample;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Book;

import java.util.Objects;

public class BookItemController {
    @FXML
    private ImageView imgbook;
    @FXML
    private Label authorbook;
    @FXML
    private Label ratingbook;

    @FXML
    private Label titlebook;

    public void setData(Book book) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        imgbook.setImage(image);
        titlebook.setText(book.getName());
        authorbook.setText(book.getAuthor());
    }

}
