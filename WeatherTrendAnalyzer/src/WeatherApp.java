import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WeatherApp extends Application {

    private WeatherAnalyzer analyzer = new WeatherAnalyzer();
    private LineChart<Number, Number> lineChart;
    private Label summaryLabel;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Weather Trend Analyzer");

        ComboBox<String> cityDropdown = new ComboBox<>();
        cityDropdown.getItems().addAll("Pune", "Mumbai");
        cityDropdown.setPromptText("Select City");

        Button loadButton = new Button("Load Data");
        summaryLabel = new Label("Summary will appear here.");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Day");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Temperature (°C)");
        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Temperature Trend");

        VBox topPane = new VBox(10, cityDropdown, loadButton, summaryLabel);
        topPane.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(topPane);
        root.setCenter(lineChart);

        loadButton.setOnAction(e -> {
            String selectedCity = cityDropdown.getValue();
            if (selectedCity == null) {
                summaryLabel.setText("⚠️ Please select a city.");
                return;
            }
            try {
                String filePath = selectedCity.equals("Pune") ? "pune.csv" : "mumbai.csv";
                List<WeatherData> dataList = analyzer.loadFromCSV(filePath);

                lineChart.getData().clear();
                XYChart.Series<Number, Number> tempSeries = new XYChart.Series<>();
                tempSeries.setName(selectedCity + " Temperature");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                int day = 1;
                for (WeatherData d : dataList) {
                    tempSeries.getData().add(new XYChart.Data<>(day++, d.getTempC()));
                }

                lineChart.getData().add(tempSeries);
                summaryLabel.setText(analyzer.generateSummary(dataList));

            } catch (IOException ex) {
                summaryLabel.setText(" Error loading data: " + ex.getMessage());
            }
        });

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
