package com.example.sample;

import BD.DataBase;
import Session.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import model.Reservation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AllReservationsAdminController implements Initializable {
    @FXML
    private VBox allReservationsAdminBox;
    List<Reservation> allReservations;

    @FXML
    private Button userName;
    @FXML
    ImageView imageUser;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userName.setText(SessionManager.getCurrentUser());
        String image = SessionManager.getImage();
//        imageUser.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(image))));
        allReservations = new ArrayList<>(getAllReservations());
        for (Reservation reservation:allReservations){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("reservationCard.fxml"));
            try{
                HBox cardBox = fxmlLoader.load();
                ReservationCardController reservationCardController = fxmlLoader.getController();
                reservationCardController.setData(reservation);
                allReservationsAdminBox.getChildren().add(cardBox);
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }
    public List<Reservation> getAllReservations(){
        List<Reservation> ls =new ArrayList<>();
        DataBase dataBase=new DataBase();
        Connection connection= dataBase.connect();
        String select = "SELECT B.title, B.image, U.username, U.image, R.reservationDate, R.returnDate,U.userID,B.bookID FROM books B JOIN reservations R ON B.bookID = R.bookId JOIN users U ON R.userID = U.userID";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            while(resultSet.next()){
               Reservation reservation=new Reservation();
                reservation.setBookName(resultSet.getString("title"));
                reservation.setImageBook(resultSet.getString("image"));
                reservation.setUserName(resultSet.getString("username"));
                reservation.setImageUser(resultSet.getString("U.image")); // Assuming there's a column named 'image' for users
                reservation.setReservationDate(resultSet.getDate("reservationDate"));
                reservation.setReturnDate(resultSet.getDate("returnDate"));
                reservation.setUserID(resultSet.getInt("userID"));
                reservation.setBookID(resultSet.getInt("bookID"));
                ls.add(reservation);
            }
            dataBase.closeConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public void goAllBooks(ActionEvent event){
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allBooksAdmin.fxml"));
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
    public void goToProfile(ActionEvent event){

    }
    public void goToHome(ActionEvent event) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminHome.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);
            currentStage.show();
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }

    }
    public void logout(ActionEvent event){
        try {
            SessionManager.clearSession();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(nextScene);
            currentStage.show();
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
    public void goToAllUsers(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allusers.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);
            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }

}
