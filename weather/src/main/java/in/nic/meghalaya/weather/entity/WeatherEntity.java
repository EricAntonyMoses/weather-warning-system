package in.nic.meghalaya.weather.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather_data")
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String district;
    private double dailyRain;
    private double weeklyRain;
    private String rainCategory;

    private String nowcastMessage;
    private String nowcastColor;

    private String warningType;
    private String warningLevel;
    private String warningMessage;

    private LocalDateTime lastUpdated;

    // -------- GETTERS & SETTERS --------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public double getDailyRain() { return dailyRain; }
    public void setDailyRain(double dailyRain) { this.dailyRain = dailyRain; }

    public double getWeeklyRain() { return weeklyRain; }
    public void setWeeklyRain(double weeklyRain) { this.weeklyRain = weeklyRain; }

    public String getRainCategory() { return rainCategory; }
    public void setRainCategory(String rainCategory) { this.rainCategory = rainCategory; }

    public String getNowcastMessage() { return nowcastMessage; }
    public void setNowcastMessage(String nowcastMessage) { this.nowcastMessage = nowcastMessage; }

    public String getNowcastColor() { return nowcastColor; }
    public void setNowcastColor(String nowcastColor) { this.nowcastColor = nowcastColor; }

    public String getWarningType() { return warningType; }
    public void setWarningType(String warningType) { this.warningType = warningType; }

    public String getWarningLevel() { return warningLevel; }
    public void setWarningLevel(String warningLevel) { this.warningLevel = warningLevel; }

    public String getWarningMessage() { return warningMessage; }
    public void setWarningMessage(String warningMessage) { this.warningMessage = warningMessage; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
