package com.example.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AllBooksAdminController implements Initializable {
    @FXML
    private VBox allBooksAdminBox;
    private List<Book> allBooksAdmin;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allBooksAdmin = new ArrayList<>(getAllBooksAdmin());
        for (Book book:allBooksAdmin){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("cardBookAdmin.fxml"));
            try{
                HBox cardBox = fxmlLoader.load();
                CardBookAdminController cardBookAdminController = fxmlLoader.getController();
                cardBookAdminController.setData(book);
                allBooksAdminBox.getChildren().add(cardBox);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    public void addBook(ActionEvent event){
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addNewBookAdmin.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);

            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
    public List<Book> getAllBooksAdmin(){
        List<Book> ls =new ArrayList<>() ;
        Book book = new Book();
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T.keyosaki");
        book.setName("Rich Dad Poor Dad");
        ls.add(book);

        book = new Book();
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T.keyosaki");
        book.setName("Rich Dad Poor Dad");
        ls.add(book);
        book = new Book();
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T.keyosaki");
        book.setName("Rich Dad Poor Dad");
        ls.add(book);
        book = new Book();
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T.keyosaki");
        book.setName("Rich Dad Poor Dad");
        ls.add(book);
        book = new Book();
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T.keyosaki");
        book.setName("Rich Dad Poor Dad");
        ls.add(book);
        book = new Book();
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T.keyosaki");
        book.setName("Rich Dad Poor Dad");
        ls.add(book);
        book = new Book();
        book.setImageSrc("imgs/rich.jpeg");
        book.setAuthor("Robert T.keyosaki");
        book.setName("Rich Dad Poor Dad");
        ls.add(book);
        return ls;
    }


}
