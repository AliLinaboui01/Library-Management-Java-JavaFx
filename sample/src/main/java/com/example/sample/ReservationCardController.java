package com.example.sample;

import BD.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Reservation;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class ReservationCardController {
    @FXML
    private ImageView UserImage;

    @FXML
    private Label UserName;

    @FXML
    private Label bookName;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label reservationDate;

    @FXML
    private Label returnDate;
    private int userId;
    private int bookID;

    public void setData(Reservation reservation){
        UserImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(reservation.getImageUser()))));
        bookImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(reservation.getImageBook()))));
        UserName.setText(reservation.getUserName());
        bookName.setText(reservation.getBookName());
        reservationDate.setText(reservation.getReservationDate().toString());
        returnDate.setText(reservation.getReturnDate().toString());
        this.userId=reservation.getUserID();
        this.bookID=reservation.getBookID();
    }
    public void returnBook(ActionEvent event){
        DataBase dataBase=new DataBase();
        Connection connection= dataBase.connect();
        String updateQuery = "UPDATE books SET availableQuantity = availableQuantity + 1 WHERE bookID = ?";
        String deleteQuery = "DELETE FROM reservations WHERE userID=?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
             PreparedStatement deleteUser = connection.prepareStatement(deleteQuery)) {
                updateStatement.setInt(1,this.bookID);
                deleteUser.setInt(1,this.userId);
                updateStatement.executeUpdate();
                deleteUser.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setUserInDarkList(ActionEvent event){
        DataBase dataBase=new DataBase();
        Connection connection= dataBase.connect();
        String insert = "INSERT INTO darckliste (date_end,idUser) VALUES (?,?)";

        if(!isUserAlreadyInDarkList()){
            try (PreparedStatement updateStatement = connection.prepareStatement(insert)) {
                LocalDateTime currentDate = LocalDateTime.now();
                LocalDateTime returnDate = currentDate.plus(10, ChronoUnit.DAYS);

                // Assuming the first placeholder is for 'date_end'
                updateStatement.setTimestamp(1, Timestamp.valueOf(returnDate));

                // Assuming the second placeholder is for 'idUser'
                updateStatement.setInt(2, this.userId);

                updateStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean isUserAlreadyInDarkList(){
        DataBase dataBase = new DataBase();
        try (Connection connection = dataBase.connect()) {
            String query = "SELECT 1 FROM favoritebooks WHERE  idUser = ? LIMIT 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, this.userId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
