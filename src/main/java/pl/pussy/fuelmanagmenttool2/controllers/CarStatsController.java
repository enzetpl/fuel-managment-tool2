package pl.pussy.fuelmanagmenttool2.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pussy.fuelmanagmenttool2.models.CarStats;
import pl.pussy.fuelmanagmenttool2.services.CarStatsService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("/api/cars")

public class CarStatsController {

    private final CarStatsService carStatsService;

    public CarStatsController(CarStatsService carStatsService) {
        this.carStatsService = carStatsService;
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<?> getCarStats(@PathVariable Long id,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") @RequestParam LocalDateTime startDate,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(carStatsService.getStats(id, startDate, endDate));
    }

}
