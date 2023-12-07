package com.example.sample;

import BD.DataBase;
import BD.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Statement;

public class Library extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {



        FXMLLoader fxmlLoader = new FXMLLoader(Library.class.getResource("profile.fxml"));





        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
      // stage.setResizable(false);

        stage.setTitle("LibraryCom");
        stage.getIcons().add(new Image("C:\\Users\\DELL 6430\\abdelmalek sadi\\Desktop\\gitcolab\\Library-Management-Java-JavaFx\\sample\\src\\main\\resources\\com\\example\\sample\\imgs\\new_bg.png"));

        stage.setScene(scene);

        stage.setFullScreen(true);


        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
