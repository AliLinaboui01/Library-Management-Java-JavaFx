package model;

public class Book {
    private String name;
    private String imageSrc;
    private String author;
    private String possibility;
    private String previewButton;

    private double rating;
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPossibility() {
        return possibility;
    }

    public void setPossibility(String possibility) {
        this.possibility = possibility;
    }

    public String getPreviewButton() {
        return previewButton;
    }

    public void setPreviewButton(String previewButton) {
        this.previewButton = previewButton;
    }
}
