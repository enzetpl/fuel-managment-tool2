package pl.pussy.fuelmanagmenttool2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pussy.fuelmanagmenttool2.models.Car;
import pl.pussy.fuelmanagmenttool2.models.Refuel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RefuelRepository extends JpaRepository<Refuel, Long> {
    List<Refuel> findAllByCarId(Long carId);
    List<Refuel> findAllByCarIdAndRefuelDateBetween(Long car_id, LocalDateTime startDate, LocalDateTime endDate);
    List<Refuel> findAllByCarUserUsernameAndRefuelDateBetween(String username, LocalDateTime startDate, LocalDateTime endDate);
}
