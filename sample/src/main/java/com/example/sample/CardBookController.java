package com.example.sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Book;

import java.util.Objects;

public class CardBookController {

    @FXML
    private Label authorName;

    @FXML
    private ImageView imageBook;

    @FXML
    private Label possibity;

    @FXML
    private Button previewButton;

    @FXML
    private Label titleBook;
    @FXML
    private Label categoryId;

    @FXML
    private Label categoryName;
    public void setData(Book book) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        imageBook.setImage(image);
        titleBook.setText(book.getName());
        authorName.setText(book.getAuthor());

    }
}
