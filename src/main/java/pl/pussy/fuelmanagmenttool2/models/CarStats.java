package pl.pussy.fuelmanagmenttool2.models;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@Data
public class CarStats {
    private double totalPrice;
    private double totalVolume;
    private int totalMileage;
    private int totalRefuels;


}
