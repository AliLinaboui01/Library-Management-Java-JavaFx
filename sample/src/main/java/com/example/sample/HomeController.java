package com.example.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    @FXML
    private HBox acadimicbook;
    private List<Book> recentlyAdded;
    private  List<Book> recomdedforyou;
    private  List<Book> acadimicbooks;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recentlyAdded = new ArrayList<>(recentlyAdded());
        recomdedforyou = new ArrayList<>(recomdedforyou());
        acadimicbooks = new ArrayList<>(recomdedforyou());
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
                bookItemController.setData(book);
                recomondebooks.getChildren().add(cardBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
       for (Book book :acadimicbooks) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("bookItem.fxml"));
            try {
                VBox bookbox = fxmlLoader.load();
                BookItemController bookItemController =fxmlLoader.getController();
                bookItemController.setData(book);
                acadimicbook.getChildren().add(bookbox);

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




    public void goToHome(ActionEvent e) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            //currentStage.setFullScreen(true);

            // Set the new scene on the stage
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);
            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }

    public void goToAllBooks(ActionEvent event) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allBooks.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene on the stage
            currentStage.setFullScreen(true);

            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);


        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }

    }
    public void goToMyshelf(ActionEvent event) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookborrowd.fxml"));
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
    public void gotoProfile(ActionEvent event) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene on the stage
            currentStage.setFullScreen(true);

            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);


        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
}
