package pl.pussy.fuelmanagmenttool2.services;

import org.springframework.stereotype.Service;
import pl.pussy.fuelmanagmenttool2.models.Car;
import pl.pussy.fuelmanagmenttool2.models.User;
import pl.pussy.fuelmanagmenttool2.repositories.CarRepository;
import pl.pussy.fuelmanagmenttool2.security.SecurityUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;
    private UserService userService;

    public CarService(CarRepository carRepository, UserService userService) {
        this.carRepository = carRepository;
        this.userService = userService;
    }

    public List<Car> getAllCars() {
        return carRepository.findAllByUserUsername(getUserUsername());
    }

    public Car createCar(Car car) {
        String username = getUserUsername();
        User user =userService.findUserByUsername(username);
        car.setUser(user);
        return carRepository.save(car);
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("car with id: " + id + " not found" ));
    }

    @Transactional
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
    private String getUserUsername() {
        return SecurityUtils.getCurrentUserUsername();
    }


}
