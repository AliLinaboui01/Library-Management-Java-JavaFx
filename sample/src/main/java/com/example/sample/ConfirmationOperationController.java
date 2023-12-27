package com.example.sample;

import BD.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConfirmationOperationController {
    int id;
    public ConfirmationOperationController(int id) {
        this.id = id;
    }
    public ConfirmationOperationController() {

    }
   public void setUserId(int id) {
        this.id = id;
   }
   public int getId(){return id;}
    @FXML
    private boolean confirmed = false; // Default value is false

    // ... other code ...

    @FXML
    private void isConfirmed(ActionEvent event) throws SQLException {
        confirmed = true;
        System.out.println("from cConfirm "+ getId());
        id = getId();
        UserCompenentController userCompenentController = new UserCompenentController();
        userCompenentController.removeUserFromDatabase(id);
            userCompenentController.goToPageAllUser(event);
        closeDialog(event);
    }

    @FXML
    private void isCancel(ActionEvent event) {
        // No need to explicitly set confirmed to false since it's already false by default
        System.out.println("from cancel "+ getId());
        closeDialog(event);
    }

    private void closeDialog(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public boolean isConfirmed() {

        return confirmed;
    }
}
