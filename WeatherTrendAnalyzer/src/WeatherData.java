public class WeatherData {
    private String date;
    private double tempC;
    private double humidity;

    public WeatherData(String date, double tempC, double humidity) {
        this.date = date;
        this.tempC = tempC;
        this.humidity = humidity;
    }

    public String getDate() { return date; }
    public double getTempC() { return tempC; }
    public double getHumidity() { return humidity; }
}
