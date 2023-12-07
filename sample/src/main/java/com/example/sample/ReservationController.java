package com.example.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReservationController {
    @FXML
    private Button borrowButton;
    @FXML
    private Button cancelButton;
    public void borrow(ActionEvent event){

    }
    public void cancel(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
