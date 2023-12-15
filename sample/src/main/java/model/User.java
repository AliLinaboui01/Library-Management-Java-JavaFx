package model;

import java.util.Date;

public class User {
    private int userID;
    private String Cne;
    private String email;
    private String firstName;
    private String image;
    private String lastName;
    private String password;
    private String username;
    private String userType;
    private Date createdDate;

    // Constructors, getters, and setters
    public User() {

    }

    // Parameterized constructor
    public User(String Cne, String email, String firstName, String image, String lastName, String password, String username, String userType,Date createdDate) {
        this.Cne = Cne;
        this.email = email;
        this.firstName = firstName;
        this.image = image;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        this.userType = userType;
        this.createdDate = createdDate;
    }

    public void setCreatedDate(Date createdDate){
        this.createdDate = createdDate;
    }
    public Date getCreatedDate(){
        return createdDate;
    }

    // Getters and Setters

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCne() {
        return Cne;
    }

    public void setCne(String cne) {
        Cne = cne;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
