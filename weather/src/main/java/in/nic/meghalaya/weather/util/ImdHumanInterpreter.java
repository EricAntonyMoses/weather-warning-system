package in.nic.meghalaya.weather.util;

import in.nic.meghalaya.weather.entity.WeatherEntity;
import in.nic.meghalaya.weather.dto.HumanWeatherDTO;

public class ImdHumanInterpreter {

    public static HumanWeatherDTO toHuman(WeatherEntity e) {
        HumanWeatherDTO dto = new HumanWeatherDTO();

        dto.district = e.getDistrict();

        dto.nowcastSummary =
            e.getNowcastMessage() + " (" + e.getNowcastColor() + ")";

        dto.rainfallSummary =
            ImdCodeMapper.RAIN_MAP.getOrDefault(
                e.getRainCategory(), "Unknown"
            ) + " (" + e.getDailyRain() + " mm today)";

        dto.warningSummary =
            (e.getWarningMessage() == null || e.getWarningMessage().isEmpty())
                ? "No warnings"
                : e.getWarningMessage();

        dto.lastUpdated = e.getLastUpdated().toString();

        return dto;
    }
}
