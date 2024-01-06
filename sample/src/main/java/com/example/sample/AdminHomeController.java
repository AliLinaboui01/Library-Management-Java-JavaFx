package com.example.sample;

import BD.DataBase;
import Session.SessionManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private Button userName;
    @FXML
    private ImageView imageUser;
    @FXML
    private Label contributeBooks;

    @FXML
    private Label numberOfBooks;

    @FXML
    private Label numberOfReservation;

    @FXML
    private Label numberOfUsers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userName.setText(SessionManager.getCurrentUser());
        String image = SessionManager.getImage();
//        imageUser.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(image))));
        numberOfUsers.setText(Integer.toString(getCountUser()));
        numberOfBooks.setText(Integer.toString(getCountBooks()));
        numberOfReservation.setText(Integer.toString(getCountReservations()));
        contributeBooks.setText(Integer.toString(getCountContributeBooks()));

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Books",getCountBooks() ));
        series.getData().add(new XYChart.Data<>("Users", getCountUser()));
        series.getData().add(new XYChart.Data<>("Reservations", getCountReservations()));
        series.getData().add(new XYChart.Data<>("Contribute", getCountContributeBooks()));


        Platform.runLater(() -> {
            try {
                series.getNode().setStyle("-fx-stroke: #0561FC;");
                for (XYChart.Data<String, Number> data : series.getData()) {
                    data.getNode().setStyle("-fx-background-color: #0561FC, white;");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        lineChart.getData().add(series);
    }
@FXML
    private void goToPageUsers(ActionEvent event) {
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
    public void goToBooks(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allBooksAdmin.fxml"));
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
    public void goToHome(ActionEvent event){
        try {
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
    public void goToReservation(ActionEvent event){
        try {
            SessionManager.clearSession();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allReservationsAdmin.fxml"));
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

    public int getCountUser(){
        DataBase dataBase =new DataBase();
        Connection connection= dataBase.connect();
        String query= "SELECT COUNT(*) FROM users";
        return getStatistic(connection, query);

    }
    public int getCountBooks(){
        DataBase dataBase =new DataBase();
        Connection connection= dataBase.connect();
        String query= "SELECT COUNT(*) FROM books";
        return getStatistic(connection, query);
    }
    public int getCountReservations(){
        DataBase dataBase =new DataBase();
        Connection connection= dataBase.connect();
        String query= "SELECT COUNT(*) FROM reservations";
        return getStatistic(connection, query);

    }
    public int getCountContributeBooks(){
        DataBase dataBase =new DataBase();
        Connection connection= dataBase.connect();
        String query= "SELECT COUNT(*) FROM contributbook";
        return getStatistic(connection, query);

    }

    private int getStatistic(Connection connection, String query) {
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet =statement.executeQuery(query);
            if(resultSet.next()){
                int conte = resultSet.getInt(1);
                return conte;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return -1;
    }
    @FXML
    void goTocontibute(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmContributionAdmin.fxml"));
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
