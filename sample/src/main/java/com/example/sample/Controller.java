package com.example.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Book;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private HBox cardLayout;

    @FXML
    private GridPane bookContainer;
    private List<Book> recentlyAdded;
    private List<Book> recommended;
    @FXML
    private ChoiceBox<String> myChoiceBox;
    String[] profileChoice={"Logout","Profile"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoiceBox.getItems().addAll(profileChoice);
//                myChoiceBox.setOnAction(this::getFood);
        recentlyAdded = new ArrayList<>(recentlyAdded());
        recommended = new ArrayList<>(books());
        int column = 0;
        int row = 1;
        try{
            for (Book book : recentlyAdded) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(book);
                cardLayout.getChildren().add(cardBox);

            }
            for (Book book : recommended) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("book.fxml"));
                VBox bookBox = fxmlLoader.load();
                BookController bookController = fxmlLoader.getController();
                bookController.setData(book);
                if(column == 6 ){
                    column = 0;
                    ++row;
                }
                bookContainer.add(bookBox,column++,row);
                GridPane.setMargin(bookBox, new Insets(10));
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private List<Book> books(){
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
        ls.add(book);book = new Book();
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
        ls.add(book);book = new Book();
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
        return ls;
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
        return ls;
    }

}
