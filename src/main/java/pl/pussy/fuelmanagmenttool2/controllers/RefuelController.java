package pl.pussy.fuelmanagmenttool2.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.pussy.fuelmanagmenttool2.models.Car;
import pl.pussy.fuelmanagmenttool2.models.Refuel;
import pl.pussy.fuelmanagmenttool2.services.RefuelService;

import javax.validation.Valid;
import java.net.URI;
import java.sql.Ref;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RefuelController {

    private RefuelService refuelService;

    public RefuelController(RefuelService refuelService) {
        this.refuelService = refuelService;
    }

    @GetMapping("/cars/{carId}/refuels")
    public ResponseEntity<List<Refuel>> getAllCars(@PathVariable Long carId) {
        return ResponseEntity.ok(refuelService.getAllRefuelsByCarId(carId));
    }
    @PostMapping("/cars/{carId}/refuels")
    public ResponseEntity<?> createCar(@PathVariable Long carId, @Valid @RequestBody Refuel refuel) {
        System.out.println(refuel);
        Refuel savedRefuel = refuelService.createRefuel(refuel, carId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedRefuel.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/cars/{carId}/refuels/{refuelId}")
    public void deleteCar(@PathVariable Long carId, @PathVariable Long refuelId)
    {
        refuelService.deleteRefuel(refuelId);
    }

    @PatchMapping("/cars/{carId}/refuels/{refuelId}")
    public ResponseEntity<?> updateCar(@PathVariable Long carId, @PathVariable Long refuelId, @RequestBody Refuel refuel) {
        refuelService.createRefuel(refuel, carId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cars/{carId}/refuels/{refuelId}")
    public ResponseEntity<Refuel> getCar(@PathVariable Long refuelId) {
        Refuel refuel = refuelService.getRefuelById(refuelId);
        return ResponseEntity.ok(refuel);
    }
}
