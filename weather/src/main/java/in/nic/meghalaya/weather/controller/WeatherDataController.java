package in.nic.meghalaya.weather.controller;

import in.nic.meghalaya.weather.dto.WeatherDTO;
import in.nic.meghalaya.weather.service.ImdWeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class WeatherDataController {

    private final ImdWeatherService service;

    public WeatherDataController(ImdWeatherService service) {
        this.service = service;
    }

    @GetMapping("/weather")
    public Collection<WeatherDTO> getAllWeather() {
        return service.getAll();
    }
}
