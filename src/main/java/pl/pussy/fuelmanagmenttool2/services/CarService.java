package pl.pussy.fuelmanagmenttool2.services;

import org.springframework.stereotype.Service;
import pl.pussy.fuelmanagmenttool2.models.Car;
import pl.pussy.fuelmanagmenttool2.repositories.CarRepository;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }
}
