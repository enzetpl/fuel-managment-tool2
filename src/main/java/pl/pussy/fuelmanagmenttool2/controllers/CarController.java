package pl.pussy.fuelmanagmenttool2.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.pussy.fuelmanagmenttool2.models.Car;
import pl.pussy.fuelmanagmenttool2.services.CarService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin
public class CarController {

    private CarService carService;


    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody Car car) {
        Car savedCar = carService.createCar(car);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedCar.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
