package in.nic.meghalaya.weather.dto;

import java.time.LocalDateTime;
import java.util.List;

public class WeatherDTO {

    private String district;

    // NOWCAST
    private String nowcastSummary;
    private String nowcastColor;

    // RAINFALL
    private double dailyRain;
    private double weeklyRain;
    private String rainCategory;

    // WARNINGS
    private String warningType;
    private String warningLevel;
    private String warningMessage;

    // 5 day forecast (new)
    private List<String> forecast5Days;

    private LocalDateTime lastUpdated;

    // ---------- getters & setters ----------

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getNowcastSummary() { return nowcastSummary; }
    public void setNowcastSummary(String nowcastSummary) { this.nowcastSummary = nowcastSummary; }

    public String getNowcastColor() { return nowcastColor; }
    public void setNowcastColor(String nowcastColor) { this.nowcastColor = nowcastColor; }

    public double getDailyRain() { return dailyRain; }
    public void setDailyRain(double dailyRain) { this.dailyRain = dailyRain; }

    public double getWeeklyRain() { return weeklyRain; }
    public void setWeeklyRain(double weeklyRain) { this.weeklyRain = weeklyRain; }

    public String getRainCategory() { return rainCategory; }
    public void setRainCategory(String rainCategory) { this.rainCategory = rainCategory; }

    public String getWarningType() { return warningType; }
    public void setWarningType(String warningType) { this.warningType = warningType; }

    public String getWarningLevel() { return warningLevel; }
    public void setWarningLevel(String warningLevel) { this.warningLevel = warningLevel; }

    public String getWarningMessage() { return warningMessage; }
    public void setWarningMessage(String warningMessage) { this.warningMessage = warningMessage; }

    public List<String> getForecast5Days() { return forecast5Days; }
    public void setForecast5Days(List<String> forecast5Days) { this.forecast5Days = forecast5Days; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
