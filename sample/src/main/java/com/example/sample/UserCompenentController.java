package com.example.sample;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Book;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserCompenentController {

    @FXML
    private Label CreatedDate;

    @FXML
    private Label UserEmail;

    @FXML
    private ImageView UserImage;

    @FXML
    private Label UserName;

    @FXML
    private Label UserType;
    public void setData(User user) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(user.getImage())));
        UserImage.setImage(image);
        UserName.setText(user.getFirstName() +  " " + user.getLastName());
        UserType.setText(user.getUserType());
        UserEmail.setText(user.getEmail());
        CreatedDate.setText(String.valueOf(user.getCreatedDate()));
    }

}
