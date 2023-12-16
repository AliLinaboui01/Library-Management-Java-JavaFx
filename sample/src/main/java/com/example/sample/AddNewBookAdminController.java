package com.example.sample;

import BD.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class AddNewBookAdminController {
    @FXML
    private TextField authorBookInput;

    @FXML
    private TextField categoryBookInput;

    @FXML
    private TextField isbnBookInput;

    @FXML
    private TextField languageBookInput;

    @FXML
    private TextField pagesBookInput;

    @FXML
    private TextField quantityBookInput;

    @FXML
    private Button submitButton;

    @FXML
    private TextArea descriptionBookInput;

    @FXML
    private TextField titleBookInput;

    @FXML
    private Button uploadImageButton;
    Alert alert = new Alert(Alert.AlertType.NONE);
    public void addNewBookHere(ActionEvent event) throws IOException {
        String title = titleBookInput.getText();
        String description = descriptionBookInput.getText();
        String category = categoryBookInput.getText();
        String isbn = isbnBookInput.getText();
        String pages = pagesBookInput.getText();
        int page = Integer.parseInt(pages);
        String quantity = quantityBookInput.getText();
        int qty = Integer.parseInt(quantity);

        if (Objects.equals(title, "")){
            System.out.println("error");

        }else{
            // database
            DataBase dataBase = new DataBase();
            dataBase.connect();
            successAlert(event);
        }

    }
    public void handleSubmit(){

    }
    public void successAlert(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("successAlert.fxml"));
        Parent root = loader.load();
        Dialog<Void> dialog = new Dialog<>();
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.getDialogPane().setContent(root);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        dialog.initOwner(currentStage);
        dialog.showAndWait();
    }
}
