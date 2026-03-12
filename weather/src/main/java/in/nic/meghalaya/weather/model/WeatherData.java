package in.nic.meghalaya.weather.model;

public class WeatherData {


    private String district;
    private double temperature;
    private double humidity;
    private String condition;

    public WeatherData() {}

    public WeatherData(String district, double temperature, double humidity, String condition) {
        this.district = district;
        this.temperature = temperature;
        this.humidity = humidity;
        this.condition = condition;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}