package com.example.sample;
import BD.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Book;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
public class BooksContributeCardController {

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
    private Book myBook;
    private  int bookID;

    public BooksContributeCardController() {}

    public void setData(Book book){

        bookTitle.setText(book.getName());
        authorName.setText(book.getAuthor());
        this.myBook=book;
        this.bookID=book.getIdBook();
        String imageUrl = book.getImageSrc();
        if (imageUrl == null || imageUrl.isEmpty()) {
            bookImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/rich.jpeg"))));
        } else {
            try {
                // Load user's image
                InputStream stream = getClass().getResourceAsStream(imageUrl);
                if (stream != null) {
                    bookImage.setImage(new Image(stream));
                } else {
                    // Provide a default image if the stream is null or invalid
                    bookImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/rich.jpeg"))));
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception (e.g., log an error message)
                // Provide a default image in case of an exception
                bookImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/rich.jpeg"))));
            }
        }
    }
    @FXML
    void removeBook(ActionEvent event) {

    }


    @FXML
    void confirmContribution(ActionEvent event) {
        System.out.println(this.bookID);
        Book book = new Book();
        book = getBookById(this.bookID);
        System.out.println(book.getName());
        addBookinBookslibrary(book);
        deleteBookById(this.bookID);
        AdminHomeController adminHomeController= new AdminHomeController();
        adminHomeController.goToBooks(event);
    }



    public Book getBookById(int bookId) {
        Book book = null;
        DataBase dataBase = new DataBase();
        Connection conn = dataBase.connect();

        String select = "SELECT * FROM contributbook WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(select)) {
            statement.setInt(1, bookId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = new Book();
                book.setAuthor(resultSet.getString("author"));
                book.setIdBook(resultSet.getInt("id"));
                book.setCategory(resultSet.getString("category"));
                book.setDescription(resultSet.getString("is a contribution by our Student "));
                book.setImageSrc(resultSet.getString("image"));
                book.setLanguage(resultSet.getString("language"));
                book.setPages(resultSet.getInt("pages"));
                book.setRating(resultSet.getFloat("rating"));
                book.setName(resultSet.getString("title"));
                // Set other properties as needed
            }

            dataBase.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }
    public void addBookinBookslibrary(Book book) {
        DataBase dataBase = new DataBase();
        Connection conn = dataBase.connect();

        String insert = "INSERT INTO books (author, availableQuantity, bookID, category, description, image, isbn, language, pages, quantity, rating, title, createdat, hyperlinkweb) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(insert)) {
            statement.setString(1, book.getAuthor());
            statement.setInt(2, 2);
            statement.setInt(3, book.getId());
            statement.setString(4, book.getCategory());
            statement.setString(5, book.getDescription());
            statement.setString(6, book.getImageSrc());
            statement.setString(7,"xxx-x-xxx");
            statement.setString(8, book.getLanguage());
            statement.setInt(9, 200);
            statement.setInt(10, 1);
            statement.setFloat(11, book.getRating());
            statement.setString(12, book.getName());
            Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
            statement.setTimestamp(13,currentTimestamp);
            statement.setString(14, "www.google.com");

            // Execute the insert statement
            statement.executeUpdate();

            System.out.println("Book added successfully to the 'books' table.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBase.closeConnection();
        }
    }

    public void deleteBookById(int bookId) {
        DataBase dataBase = new DataBase();
        Connection conn = dataBase.connect();

        String delete = "DELETE FROM contributbook WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(delete)) {
            statement.setInt(1, bookId);

            // Execute the delete statement
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book with ID " + bookId + " deleted successfully from 'contributbook' table.");
            } else {
                System.out.println("No book found with ID " + bookId + " in 'contributbook' table.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBase.closeConnection();
        }
    }

}

