package in.nic.meghalaya.weather.service;

import in.nic.meghalaya.weather.entity.WeatherEntity;
import in.nic.meghalaya.weather.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherStorageService {

    private final WeatherRepository repo;

    public WeatherStorageService(WeatherRepository repo) {
        this.repo = repo;
    }

    public void saveOrUpdate(WeatherEntity e) {
        e.setLastUpdated(LocalDateTime.now());
        repo.save(e);
    }

    public List<WeatherEntity> getAll() {
        return repo.findAll();
    }
}
