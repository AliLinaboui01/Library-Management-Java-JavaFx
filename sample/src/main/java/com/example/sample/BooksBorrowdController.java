package com.example.sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.Book;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BooksBorrowdController implements Initializable {
    @FXML
    private GridPane gridborrowdbook;

    private List<Book> borrowedBooks;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        borrowedBooks = getBorrowedBooks();

        int column = 0;
        int row = 1;

        for (Book book : borrowedBooks) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("borrowdCard.fxml"));
            VBox bookBox;
            try {
                bookBox = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            BorrowdCardController borrowdCardController = fxmlLoader.getController();
            borrowdCardController.setData(book);




            // Check if we need to move to the next row
            if (column == 3) {
                column = 0;
                ++row;
            }

            gridborrowdbook.add( bookBox, column++, row);
            GridPane.setMargin( bookBox, new Insets(15));

        }
    }

    private List<Book> getBorrowedBooks() {
        List<Book> ls = new ArrayList<>();
        Book book = new Book();
        book.setName("RICH DAD POOR DAD");
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T. Kiyosaki");
        book.setRating("4/5");
        ls.add(book);

        book = new Book();
        book.setName("THW WARREN BUFFETT");
        book.setImageSrc("imgs/téléchargement.jpeg");
        book.setAuthor("Robert G. Hagstrom");
        book.setRating("3/5");
        ls.add(book);

        book = new Book();
        book.setName("THW WARREN BUFFETT");
        book.setImageSrc("imgs/téléchargement.jpeg");
        book.setAuthor("Robert G. Hagstrom");
        ls.add(book);



        book = new Book();
        book.setName("RICH DAD POOR DAD");
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);

        book = new Book();
        book.setName("THW WARREN BUFFETT");
        book.setImageSrc("imgs/téléchargement.jpeg");
        book.setAuthor("Robert G. Hagstrom");
        ls.add(book);
        book = new Book();
        book.setName("RICH DAD POOR DAD");
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T. Kiyosaki");
        ls.add(book);

        return ls;
    }
}
