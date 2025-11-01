# Weather Trend Analyzer (JavaFX Project)

##  Overview
This is a JavaFX-based desktop application that visualizes long-term weather trends such as temperature changes across cities like Mumbai and Pune
using historical CSV data.

##  Requirements
- **Java JDK 17 or later** (JDK 24 recommended)
- **JavaFX SDK 25.0.1**
- **IntelliJ IDEA Community Edition** (or any Java IDE)

##  Project Structure
```
WeatherTrendAnalyzer/
│
├── src/
│   ├── Main.java
│   ├── WeatherApp.java
│   ├── WeatherData.java
│   ├── WeatherChart.java
│   ├── CSVReader.java
│   └── resources/
│       ├── mumbai_weather.csv
│       └── pune_weather.csv
│
└── README.txt
```

##  How to Run in IntelliJ IDEA

1. **Open the project** in IntelliJ IDEA.
2. Go to **File → Project Structure → SDKs** and make sure your JDK (e.g. `jdk-24`) is added.
3. Download **JavaFX SDK** and extract it to:
   ```
   C:\Program Files\javafx-sdk-25.0.1
   ```
4. Open **Run → Edit Configurations → VM Options** and paste:
   ```
   --module-path "C:\Program Files\javafx-sdk-25.0.1\lib" --add-modules javafx.controls,javafx.fxml
   ```
5. Run the `WeatherApp` class.

##  Common Issues
- **Error: Module not found** → Make sure the JavaFX path is correctly set in *VM options*.
- **App not responding with large CSVs** → Use smaller datasets (e.g., ~200–600 rows).
- **Warning about restricted methods** → Add this optional flag to VM options:
  ```
  --enable-native-access=javafx.graphics
  ```

## Sample Data
- `mumbai_weather.csv` → ~600 rows
- `pune_weather.csv` → ~200 rows

## Features
- Load CSV weather data
- Display year-wise temperature trends using charts
- Smooth UI with JavaFX controls
- Handles moderate data efficiently

---
 2025 Weather Trend Analyzer | Developed by Atharva Deshmukh, Maahit Bhawsar, Prathmesh Naik
