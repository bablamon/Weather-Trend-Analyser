import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherAnalyzer {

    public List<WeatherData> loadFromCSV(String filePath) throws IOException {
        List<WeatherData> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 24) continue;
                try {
                    String date = values[0];
                    double tempC = Double.parseDouble(values[20]);
                    double humidity = Double.parseDouble(values[17]);
                    dataList.add(new WeatherData(date, tempC, humidity));
                } catch (NumberFormatException e) {
                    // skip bad data
                }
            }
        }
        return dataList;
    }

    public String generateSummary(List<WeatherData> dataList) {
        if (dataList.isEmpty()) return "No data available.";

        double max = Double.MIN_VALUE, min = Double.MAX_VALUE, sum = 0;
        for (WeatherData d : dataList) {
            double t = d.getTempC();
            if (t > max) max = t;
            if (t < min) min = t;
            sum += t;
        }

        double avg = sum / dataList.size();
        return String.format("📊 Avg: %.2f°C | Max: %.2f°C | Min: %.2f°C | Entries: %d",
                avg, max, min, dataList.size());
    }
}
