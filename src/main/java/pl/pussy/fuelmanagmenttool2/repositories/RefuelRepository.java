package pl.pussy.fuelmanagmenttool2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pussy.fuelmanagmenttool2.models.Refuel;

import java.util.List;
import java.util.Optional;

public interface RefuelRepository extends JpaRepository<Refuel, Long> {
    List<Refuel> findAllByCarId(Long carId);
}
