package in.nic.meghalaya.weather.repository;

import in.nic.meghalaya.weather.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository
        extends JpaRepository<WeatherEntity, Long> {

    Optional<WeatherEntity> findByDistrict(String district);
}
