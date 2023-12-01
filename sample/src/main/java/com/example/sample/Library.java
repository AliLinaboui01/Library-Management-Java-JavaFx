package com.example.sample;

import BD.DataBase;
import BD.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Statement;

public class Library extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(Library.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        stage.setResizable(false);
        stage.setTitle("My Library");
        stage.setScene(scene);
        stage.show();
//        DataBase bd=new DataBase();
//        bd.connect();
    }

    public static void main(String[] args) {
        launch();
    }
}
