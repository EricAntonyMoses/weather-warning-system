package in.nic.meghalaya.weather.scheduler;

import in.nic.meghalaya.weather.service.ImdWeatherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherScheduler {

    private final ImdWeatherService imd;

    public WeatherScheduler(ImdWeatherService imd) {
        this.imd = imd;
    }

    @Scheduled(cron = "0 0 */3 * * *") // every 3 hours
    public void refresh() {
        imd.refresh();
        System.out.println("IMD weather refreshed");
    }
}
