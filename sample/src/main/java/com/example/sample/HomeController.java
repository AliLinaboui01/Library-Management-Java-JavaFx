package com.example.sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.Book;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private HBox bookCompenenet;
    @FXML
    private HBox recomondebooks;
    @FXML
    private HBox recomondebooks2;
    private List<Book> recentlyAdded;
    private  List<Book> recomdedforyou;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recentlyAdded = new ArrayList<>(recentlyAdded());
        recomdedforyou = new ArrayList<>(recomdedforyou());
        for (Book book : recentlyAdded) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("bookcompnent.fxml"));
            try {
                VBox cardBox = fxmlLoader.load();
                BookcompenentController bookcompenentController =fxmlLoader.getController();
                bookcompenentController.setData(book);
                bookCompenenet.getChildren().add(cardBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        for (Book book : recomdedforyou) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("bookItem.fxml"));
            try {
                VBox cardBox = fxmlLoader.load();
                BookItemController bookItemController =fxmlLoader.getController();
                bookItemController .setData(book);
                recomondebooks.getChildren().add(cardBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        for (Book book : recomdedforyou) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("bookItem.fxml"));
            try {
                VBox bookbox = fxmlLoader.load();
                BookItemController bookItemController =fxmlLoader.getController();
                bookItemController .setData(book);
                recomondebooks.getChildren().add(bookbox);
                recomondebooks2.getChildren().add(bookbox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


    private List<Book> recentlyAdded() {
        List<Book> ls = new ArrayList<>();
        Book book = new Book();
        book.setName("RICH DAD\nPOOR DAD");
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);

        book = new Book();
        book.setName("THW WARREN\nBUFFETT WAY");
        book.setImageSrc("imgs/téléchargement.jpeg");
        book.setAuthor("Robert G. Hagstrom");
        ls.add(book);




        book = new Book();
        book.setName("THE RICHEST\nMAN IN BABYLON");
        book.setImageSrc("imgs/RICHEST.jpeg");
        book.setAuthor("George Samuel Clason");
        ls.add(book);


        book = new Book();
        book.setName("THW WARREN\nBUFFETT WAY");
        book.setImageSrc("imgs/téléchargement.jpeg");
        book.setAuthor("Robert G. Hagstrom");
        ls.add(book);



        book = new Book();
        book.setName("RICH DAD\nPOOR DAD");
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);


        book = new Book();
        book.setName("THE RICHEST\nMAN IN BABYLON");
        book.setImageSrc("imgs/RICHEST.jpeg");
        book.setAuthor("George Samuel Clason");
        ls.add(book);
        return ls;
    }


    private List<Book>recomdedforyou() {
        List<Book> ls = new ArrayList<>();
        Book book = new Book();
        book.setName("RICH DAD\nPOOR DAD");
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);

        book = new Book();
        book.setName("THW WARREN\nBUFFETT WAY");
        book.setImageSrc("imgs/téléchargement.jpeg");
        book.setAuthor("Robert G. Hagstrom");
        ls.add(book);




        book = new Book();
        book.setName("THE RICHEST\nMAN IN BABYLON");
        book.setImageSrc("imgs/RICHEST.jpeg");
        book.setAuthor("George Samuel Clason");
        ls.add(book);


        book = new Book();
        book.setName("THW WARREN\nBUFFETT WAY");
        book.setImageSrc("imgs/téléchargement.jpeg");
        book.setAuthor("Robert G. Hagstrom");
        ls.add(book);



        book = new Book();
        book.setName("RICH DAD\nPOOR DAD");
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);

        book = new Book();
        book.setName("THW WARREN\nBUFFETT WAY");
        book.setImageSrc("imgs/téléchargement.jpeg");
        book.setAuthor("Robert G. Hagstrom");
        ls.add(book);
        book = new Book();
        book.setName("RICH DAD\nPOOR DAD");
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);

        book = new Book();
        book.setName("THW WARREN\nBUFFETT WAY");
        book.setImageSrc("imgs/téléchargement.jpeg");
        book.setAuthor("Robert G. Hagstrom");
        ls.add(book);




        book = new Book();
        book.setName("THE RICHEST\nMAN IN BABYLON");
        book.setImageSrc("imgs/RICHEST.jpeg");
        book.setAuthor("George Samuel Clason");
        ls.add(book);




        book = new Book();
        book.setName("THE RICHEST\nMAN IN BABYLON");
        book.setImageSrc("imgs/RICHEST.jpeg");
        book.setAuthor("George Samuel Clason");
        ls.add(book);
        book = new Book();
        book.setName("RICH DAD\nPOOR DAD");
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);


        book = new Book();
        book.setName("THE RICHEST\nMAN IN BABYLON");
        book.setImageSrc("imgs/RICHEST.jpeg");
        book.setAuthor("George Samuel Clason");
        ls.add(book);
        return ls;
    }
}
