package com.example.sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {
    @FXML
    private LineChart<String, Number> lineChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
//        series.setName("Sample Data");

        series.getData().add(new XYChart.Data<>("Monday", 5));
        series.getData().add(new XYChart.Data<>("Tuesday", 10));
        series.getData().add(new XYChart.Data<>("Wednesday", 5));
        series.getData().add(new XYChart.Data<>("Thursday", 2));
        series.getData().add(new XYChart.Data<>("Friday", 12));
        series.getData().add(new XYChart.Data<>("Saturday", 15));
        series.getData().add(new XYChart.Data<>("Sunday", 9));

        Platform.runLater(() -> {
            try {
                series.getNode().setStyle("-fx-stroke: #0561FC;");

                // Set the color of the data points
                for (XYChart.Data<String, Number> data : series.getData()) {
                    data.getNode().setStyle("-fx-background-color: #0561FC, white;");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });



        lineChart.getData().add(series);
    }
}
