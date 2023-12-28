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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class AddNewBookAdminController {
    //start error
    @FXML
    private Label errorCategory;
    @FXML
    private Label errorAuthor;
    @FXML
    private Label errorLanguage;

    @FXML
    private Label errorPages;

    @FXML
    private Label errorQuantity;

    @FXML
    private Label errorTitle;
    @FXML
    private Label errorImage;
    @FXML
    private Label errorDescription;

    @FXML
    private Label errorIsbn;
    @FXML
    private Label errorRatting;
    //end error

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
    private TextField rattingBookInput;

    @FXML
    private TextArea descriptionBookInput;

    @FXML
    private TextField titleBookInput;

    @FXML
    private Button uploadImageButton;
    private String imagePath;
    public void addNewBookHere(ActionEvent event){
        String title = titleBookInput.getText();
        String description = descriptionBookInput.getText();
        String category = categoryBookInput.getText();
        String isbn = isbnBookInput.getText();
        String pages = pagesBookInput.getText();
        int page;
        String quantity = quantityBookInput.getText();
        int qty ;
        String language = languageBookInput.getText();
        String author = authorBookInput.getText();
        String ratting = rattingBookInput.getText();
        float rattingDb ;

        // insert in database
        try {
            if (handleSubmit(title,description,category,isbn,pages,quantity,language,author,ratting)){
                if (isNumeric(pages) && isNumeric(quantity) && isNumeric(ratting) && !imagePath.isEmpty()){
                    page = Integer.parseInt(pages);
                    qty = Integer.parseInt(quantity);
                    rattingDb = Float.parseFloat(ratting);
                    DataBase dataBase = new DataBase();
                    Connection connect = dataBase.connect();
                    Statement statement = connect.createStatement();
                    String insertRequest = "INSERT INTO books (author,availableQuantity,category,description,image,isbn,language,pages,quantity,rating,title) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connect.prepareStatement(insertRequest);
                    preparedStatement.setString(1,author);
                    preparedStatement.setInt(2,qty);
                    preparedStatement.setString(3,category);
                    preparedStatement.setString(4,description);
                    preparedStatement.setString(5,imagePath);
                    preparedStatement.setString(6,isbn);
                    preparedStatement.setString(7,language);
                    preparedStatement.setInt(8,page);
                    preparedStatement.setInt(9,qty);
                    preparedStatement.setFloat(10,rattingDb);
                    preparedStatement.setString(11,title);
                    preparedStatement.executeUpdate();
                    successAlert(event);
                    goToAllBook(event);
                }
                else {
                    if(!isNumeric(pages)){
                        errorPages.setText("the pages input should be a number!");
                    }
                    if (!isNumeric(quantity)){
                        errorQuantity.setText("the quantity input should be a number!");
                    }
                    if (!isNumeric(ratting)){
                        errorRatting.setText("the ratting should be a number");
                    }
                }

            }else{
//                errorAlert(event);
                if(title.isEmpty()){
                    errorTitle.setText("the title input should be not empty!");
                }else {
                    errorTitle.setText("");
                }
                if (category.isEmpty()) {
                    errorCategory.setText("the category input should be not empty!");
                }else {
                    errorCategory.setText("");
                }
                if (isbn.isEmpty()) {
                    errorIsbn.setText("the desc should be not empty!");
                }else {
                    errorIsbn.setText("");
                }
                if (pages.isEmpty()) {
                    errorPages.setText("the pages input should be not empty!");
                }else {
                    errorPages.setText("");
                }
                if (quantity.isEmpty()) {
                    errorQuantity.setText("the quantity input should be not empty!");
                }else {
                    errorQuantity.setText("");
                }
                if (language.isEmpty()) {
                    errorLanguage.setText("the language input should be not empty!");

                }else {
                    errorLanguage.setText("");
                }
                if (author.isEmpty()) {
                    errorAuthor.setText("the author name should be not empty!");
                }else {
                    errorAuthor.setText("");
                }
                if (description.isEmpty()) {
                    errorDescription.setText("the description input should be not empty!");
                }else {
                    errorDescription.setText("");
                }
                if (imagePath==null) {
                    errorImage.setText("the image is required!");
                }else {
                    errorImage.setText("");
                }
                if(ratting.isEmpty()){
                    errorRatting.setText("the ratting should be not empty");
                }else{
                    errorRatting.setText("");
                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
    public boolean handleSubmit(String titleBook,String descriptionBook,String categoryBook,String isbnBook,String pagesBook,String quantityBook,String languageBook,String authorBook , String rattingBook){
        return  !titleBook.trim().isEmpty() && !descriptionBook.trim().isEmpty() && !categoryBook.trim().isEmpty() && !isbnBook.trim().isEmpty() && !pagesBook.trim().isEmpty() && !quantityBook.trim().isEmpty()  && !languageBook.trim().isEmpty() && !authorBook.trim().isEmpty() && !rattingBook.trim().isEmpty();
    }
    public void uploadImage(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        Stage stage = (Stage) uploadImageButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                // Get the directory to save images outside the project (adjust as needed)
                String imageDirectory = "src/main/resources/com/example/sample/imgs/books/images";

                // Generate a unique file name based on the original file name
                String originalFileName = selectedFile.getName();
                String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
                imagePath ="imgs/books/images/"+uniqueFileName;
                // Create a new file in the destination directory
                Path destinationPath = Paths.get(System.getProperty("user.dir"), imageDirectory, uniqueFileName);
                // Copy the selected file to the destination directory
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Image uploaded successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception (e.g., show an error message)
            }
        }
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
    public void errorAlert(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("errorAlert.fxml"));
        Parent root = loader.load();
        Dialog<Void> dialog = new Dialog<>();
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.getDialogPane().setContent(root);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        dialog.initOwner(currentStage);
        dialog.showAndWait();
    }
    public void goToAllBook(ActionEvent event){
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
    public boolean isNumeric(String input) {
        return input.matches("\\d+");
    }
}
