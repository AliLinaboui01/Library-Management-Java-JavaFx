package com.example.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Book;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FavoriteBookController implements Initializable {
    @FXML
    private VBox favoriteBookVbox;
    private List<Book> favoriteBooks;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        favoriteBooks = new ArrayList<>(favoriteBooks());
        for (Book book : favoriteBooks) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("cardBook.fxml"));
            try {
                HBox cardBox = fxmlLoader.load();
                CardBookController cardBookController =fxmlLoader.getController();
                cardBookController.setData(book);
                favoriteBookVbox.getChildren().add(cardBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    private List<Book> favoriteBooks(){
        List<Book> ls = new ArrayList<>();
        Book book = new Book();
        book.setName("RICH DAD POOR DAD");
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);

        book = new Book();
        book.setName("THW WARREN BUFFETT WAY");
        book.setImageSrc("imgs/téléchargement.jpeg");
        book.setAuthor("Robert G. Hagstrom");
        ls.add(book);


        book = new Book();
        book.setName("THE RICHEST MAN IN BABYLON");
        book.setImageSrc("imgs/RICHEST.jpeg");
        book.setAuthor("George Samuel Clason");
        ls.add(book);



        return ls;
    }

}
