package pl.pussy.fuelmanagmenttool2.services;

import org.springframework.stereotype.Service;
import pl.pussy.fuelmanagmenttool2.models.Car;
import pl.pussy.fuelmanagmenttool2.models.Refuel;
import pl.pussy.fuelmanagmenttool2.repositories.RefuelRepository;

import javax.transaction.Transactional;
import java.sql.Ref;
import java.util.List;

@Service
public class RefuelService {

    private RefuelRepository refuelRepository;
    private CarService carService;

    public RefuelService(RefuelRepository refuelRepository, CarService carService) {
        this.refuelRepository = refuelRepository;
        this.carService = carService;
    }

    @Transactional
    public Refuel createRefuel(Refuel refuel, Long carId) {
        Car car = carService.getCarById(carId);
        refuel.setCar(car);
        return refuelRepository.save(refuel);
    }

    public List<Refuel> getAllRefuelsByCarId(Long carId) {
        return refuelRepository.findAllByCarId(carId);
    }

    public void deleteRefuel(Long refuelId) {
        refuelRepository.deleteById(refuelId);
    }

    public Refuel getRefuelById(Long refuelId) {
        return refuelRepository.findById(refuelId)
                .orElseThrow(() -> new IllegalArgumentException("car with id: " + refuelId + " not found" ));
    }
}
