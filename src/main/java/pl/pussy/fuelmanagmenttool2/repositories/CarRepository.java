package pl.pussy.fuelmanagmenttool2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pussy.fuelmanagmenttool2.models.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByUserUsername(String username);

}
