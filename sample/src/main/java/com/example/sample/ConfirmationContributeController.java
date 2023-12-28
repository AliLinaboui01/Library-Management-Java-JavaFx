package com.example.sample;

import BD.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Book;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ConfirmationContributeController implements Initializable {

    @FXML
    private VBox allContributes;

    @FXML
    private Button userName;


    private List<Book> allBooksAdmin;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allBooksAdmin = new ArrayList<>(getAllBooksAdmin());
        for (Book book:allBooksAdmin){
            System.out.println(book.getName());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("bookContributeCompenent.fxml"));
            try{
                HBox cardBox = fxmlLoader.load();
               BooksContributeCardController cardBookAdminController = fxmlLoader.getController();
                cardBookAdminController.setData(book);
                allContributes.getChildren().add(cardBox);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    public List<Book> getAllBooksAdmin() {
        List<Book> ls = new ArrayList<>();
        DataBase dataBase = new DataBase();
        Connection conn = dataBase.connect();
        String select = "SELECT * FROM contributbook order by id desc ";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()) {
                Book book = new Book();  // Create a new Book object for each iteration

                book.setAuthor(resultSet.getString("author"));
                book.setIdBook(resultSet.getInt("id"));
                book.setCategory(resultSet.getString("category"));
                book.setDescription(resultSet.getString("description"));
                book.setImageSrc(resultSet.getString("image"));
                book.setLanguage(resultSet.getString("language"));
                book.setPages(resultSet.getInt("pages"));
                book.setRating(resultSet.getFloat("rating"));
                book.setName(resultSet.getString("title"));

                ls.add(book);
            }

            dataBase.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ls;
    }



}
