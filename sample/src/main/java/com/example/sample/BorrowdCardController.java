package com.example.sample;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import model.Book;

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
    public void setData(Book book) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        BookImg.setImage(image);
        BookTitle.setText(book.getName());
        BookAuthor.setText(book.getAuthor());
        BookRating.setText(book.getRating());
    }

}
