package com.example.sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Book;
import javafx.fxml.FXML;

import java.io.InputStream;
import java.util.Objects;

public class BookcompenentController {



    @FXML
    private ImageView imagebc;


    public void setData(Book book){
        // Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        // imagebc.setImage(image);
        if (book.getImageSrc() == null || book.getImageSrc().isEmpty()) {
            imagebc.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/831.jpg"))));
        } else {
            try {
                // Load user's image
                InputStream stream = getClass().getResourceAsStream(book.getImageSrc());
                if (stream != null) {
                    imagebc.setImage(new Image(stream));
                } else {
                    // Provide a default image if the stream is null or invalid
                    imagebc.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/831.jpg"))));
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception (e.g., log an error message)
                // Provide a default image in case of an exception
                imagebc.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/831.jpg"))));
            }
        }

    }

}
