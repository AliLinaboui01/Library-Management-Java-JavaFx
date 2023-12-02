package com.example.sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Book;
import javafx.fxml.FXML;
import java.util.Objects;

public class BookcompenentController {



    @FXML
    private ImageView imagebc;


    public void setData(Book book){
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        imagebc.setImage(image);

    }

}
