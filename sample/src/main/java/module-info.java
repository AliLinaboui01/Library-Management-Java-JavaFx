module com.example.sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;
    requires com.google.zxing;
    requires com.google.zxing.javase;

    opens com.example.sample to javafx.fxml;
    exports com.example.sample;
    exports model;
    opens model to javafx.fxml;
}