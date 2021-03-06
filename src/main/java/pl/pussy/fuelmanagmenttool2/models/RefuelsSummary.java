package pl.pussy.fuelmanagmenttool2.models;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@Data
public class RefuelsSummary {
    private double totalPrice;
    private double totalVolume;
    private int totalMileage;
    private int totalRefuels;
    private double avgPricePerLiter;
    private double maxPricePerLiter;
    private double minPricePerLiter;

}
