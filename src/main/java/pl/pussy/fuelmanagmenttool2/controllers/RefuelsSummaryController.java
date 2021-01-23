package pl.pussy.fuelmanagmenttool2.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pussy.fuelmanagmenttool2.services.RefuelsSummaryService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class RefuelsSummaryController {

    private final RefuelsSummaryService refuelsSummaryService;

    public RefuelsSummaryController(RefuelsSummaryService refuelsSummaryService) {
        this.refuelsSummaryService = refuelsSummaryService;
    }

    @GetMapping("/cars/{carId}/stats")
    public ResponseEntity<?> getCarStats(@PathVariable Long carId,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd")
                                            @RequestParam LocalDate startDate,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd")
                                             @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(refuelsSummaryService.getStatsForCar(carId, startDate, endDate));
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getUserStats(@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                              @RequestParam LocalDate startDate,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                            @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(refuelsSummaryService.getStatsForUser(startDate, endDate));
    }

}
