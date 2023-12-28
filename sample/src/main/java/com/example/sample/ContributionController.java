package com.example.sample;

import BD.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Book;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ContributionController implements Initializable {

    @FXML
    private TextField AuthorName1;

    @FXML
    private TextField BookName;

  private int userId;

  String imagePath;
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    @FXML
    private Button uploadImagebook;
    @FXML
    private TextField category;

    @FXML
    private TextField lang;

    @FXML
    private ScrollPane MyContributions;

    @FXML
    private Button username;

    @FXML
    private HBox mycontributions;

    private List<Book>  mycontribituions;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mycontribituions= new ArrayList<>(getmyContribituions());

        for (Book book : mycontribituions) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("bookcompnent.fxml"));
            try {
                VBox cardBox = fxmlLoader.load();
                BookcompenentController bookcompenentController =fxmlLoader.getController();
                bookcompenentController.setData(book);
                mycontributions.getChildren().add(cardBox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }


    private List<Book> getmyContribituions(){
        DataBase dataBase = new DataBase();
        Connection connection = dataBase.connect();
        List<Book> books = new ArrayList<>();
        String query="SELECT * FROM contributbook WHERE userID = 1 ORDER BY id DESC  ";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setName(resultSet.getString("title"));
                book.setImageSrc(resultSet.getString("image"));
                System.out.println(book.getImageSrc());

                book.setAuthor(resultSet.getString("author"));
                book.setId(resultSet.getInt("id"));
                books.add(book);
            }
            dataBase.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }



    @FXML
    void addContribution(ActionEvent event) throws IOException {
        String author = AuthorName1.getText();
        String book = BookName.getText();
        String bookCategory = category.getText();
        String language = lang.getText();

        // Check if any of the fields is empty
        if (author.isEmpty() || book.isEmpty() || bookCategory.isEmpty() || language.isEmpty()) {
            // Display an alert if any field is empty
            dangerAlert(event);
        } else {
            // Insert data into the database
            DataBase dataBase = new DataBase();
            try (Connection connection = dataBase.connect()) {
                String sql = "INSERT INTO contributbook (author, category, language, title, userID,image) VALUES (?, ?, ?, ?,?,?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, author);
                    statement.setString(2, bookCategory);
                    statement.setString(3, language);
                    statement.setString(4, book);
                    statement.setInt(5, 1);
                    statement.setString(6,imagePath);

                    // Execute the insert statement
                    statement.executeUpdate();
                    UserCrudController userCrudController = new UserCrudController();
                    userCrudController.successAlert(event);
                    BooksBorrowdController booksBorrowdController = new BooksBorrowdController();
                    booksBorrowdController.goToHome(event);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception (e.g., display an error alert)
                dangerAlert(event);
            }

            // Do other tasks as needed

            System.out.println("Author: " + author);
            System.out.println("Book: " + book);
            System.out.println("Category: " + bookCategory);
            System.out.println("Language: " + language);
        }
    }




        public void dangerAlert(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dangerAlert.fxml"));
            Parent root = loader.load();
            Dialog<Void> dialog = new Dialog<>();
            dialog.initStyle(StageStyle.TRANSPARENT);
            dialog.getDialogPane().setContent(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            dialog.initOwner(currentStage);
            dialog.showAndWait();
        }




    @FXML
    void goToAllBook(ActionEvent event) {
        HomeController homeController = new HomeController();
        homeController.goToAllBooks(event);

    }

    @FXML
    void goToContribute(ActionEvent event) {

    }

    @FXML
    void goToHome(ActionEvent event) {
        HomeController homeController = new HomeController();
        homeController.goToHome(event);
    }

    @FXML
    void goToMyshelf(ActionEvent event) {
        HomeController homeController = new HomeController();
        homeController.goToMyshelf(event);

    }



    @FXML
    private void uploadImage()   {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        Stage stage = (Stage) uploadImagebook.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                // Get the directory to save images outside the project (adjust as needed)
                String imageDirectory = "src/main/resources/com/example/sample/imgs/books/images";

                // Generate a unique file name based on the original file name
                String originalFileName = selectedFile.getName();
                String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
                imagePath =  "imgs/books/images/" + uniqueFileName;
                // Create a new file in the destination directory
                Path destinationPath = Paths.get(System.getProperty("user.dir"), imageDirectory, uniqueFileName);
                // Copy the selected file to the destination directory
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Image replaced successfully!");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                // Handle the exception (e.g., show an error message)
            }
        }


    }



}
