package com.example.sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Book;

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
    public void setData(Book book){
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        bookImage.setImage(image);
        bookTitle.setText(book.getName());
        authorName.setText(book.getAuthor());
    }
}
