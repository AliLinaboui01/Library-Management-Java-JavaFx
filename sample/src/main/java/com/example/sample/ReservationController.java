package com.example.sample;

import BD.DataBase;
import SMTP_Mail.SendMail;
import com.example.sample.Services.GenerateQRCode;
import com.google.zxing.WriterException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ResourceBundle;

public class ReservationController  implements Initializable {
    @FXML
    private Button borrowButton;
    @FXML
    private Button cancelButton;
    private int idBook;
    private String QrPath;
    private int idUser;

    @FXML
    private TextField isbn;

    @FXML
    private TextArea purpose;
    private final SendMail sendMail = new SendMail("alilinaboui@gmail.com", "gpmo chhj lapm uzwf");



    public void borrow(ActionEvent event){
  // here we put logique of reservation

        System.out.println("from borrow confirmation "+QrPath);
        System.out.println(this.idBook +" user "+ this.idUser);
       String description = null;

        String sql = "INSERT INTO reservations ( bookID, qrCode, reservationDate, returnDate, userID,purpos) VALUES (?, ?, ?, ?, ?,?)";
        String updateQuantitySql = "UPDATE books SET availableQuantity = availableQuantity - 1 WHERE bookID = ?";

        description = purpose.getText();
          if(description == null)
            description = "Recherche";

        DataBase dataBase = new DataBase();
        Connection connection = dataBase.connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            PreparedStatement updateQuantityStatement = connection.prepareStatement(updateQuantitySql);
            preparedStatement.setInt(1, this.idBook);
            preparedStatement.setString(2, QrPath);
            Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
            preparedStatement.setTimestamp(3, currentTimestamp);
            LocalDateTime currentDate = LocalDateTime.now();
            LocalDateTime returnDate = currentDate.plus(15, ChronoUnit.DAYS);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(returnDate));
            preparedStatement.setInt(5, this.idUser);
            preparedStatement.setString(6,description);
            preparedStatement.executeUpdate();
            updateQuantityStatement.setInt(1,this.idBook);
            updateQuantityStatement.executeUpdate();
            System.out.println("Reservation inserted successfully.");



        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log, throw, etc.)

        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("successReservation.fxml"));
            Parent root = loader.load();

            // Create a new dialog
            Dialog<Void> dialog = new Dialog<>();
            dialog.initStyle(StageStyle.UNDECORATED);

            // Set the content of the dialog to the loaded FXML
            dialog.getDialogPane().setContent(root);

            // Get the Stage from the current Node
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               currentStage.close();
            // Set the owner of the dialog to the current stage
            dialog.initOwner(currentStage);
            // Show the dialog
            dialog.showAndWait();

            gotoMyShelfs(event);


        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
    public void cancel(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("hello");



    }

    public void setIds(int idBook, int idUser) throws IOException, WriterException, MessagingException {
        this.idBook = idBook;
        this.idUser = idUser;
        String isbnDatabase = this.getISBNById(idBook);
        isbn.setText(isbnDatabase);

        System.out.println("idBook: " + idBook);
        System.out.println("idUser: " + idUser);
        String pathQrCode = GenerateQRCode.generateQrCode("User "+idUser+"reserver book " + idBook );
        this.QrPath = pathQrCode;
        sendMail.SendVerificationMail("akkaouih17@gmail.com", "verificationCode");//send email

        sendMail.sendEmailWithAttachment("akkaouih17@gmail.com", "Your RESERVATION Qr CODE ","ENSAH HOCEIMA", this.QrPath);





    }


    public String getISBNById(int bookId) {
        String isbnDatabase = null;
        DataBase dataBase = new DataBase();

        String query = "SELECT isbn FROM books WHERE bookID = ?";

        try (Connection connection = dataBase.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            System.out.println();

            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                isbnDatabase = resultSet.getString("isbn");
                System.out.println(isbnDatabase);
                isbn.setText(isbnDatabase);
            } else {
                System.out.println("No ISBN found for book with ID " + bookId);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return isbnDatabase;
    }

    public void gotoMyShelfs(ActionEvent e) {
        try {
//             Load the FXML file for the register scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene nextScene = new Scene(root);

            // Get the Stage from the current Node (you can adjust this if needed)
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            //currentStage.setFullScreen(true);
            currentStage.close();


            // Set the new scene on the stage
            currentStage.setScene(nextScene);
            currentStage.setFullScreen(true);
            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }


}
