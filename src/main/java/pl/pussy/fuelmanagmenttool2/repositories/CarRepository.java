package pl.pussy.fuelmanagmenttool2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pussy.fuelmanagmenttool2.models.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
