package pl.pussy.fuelmanagmenttool2.services;

import org.springframework.stereotype.Service;
import pl.pussy.fuelmanagmenttool2.models.CarStats;
import pl.pussy.fuelmanagmenttool2.models.Refuel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarStatsService {

    private final CarService carService;
    private final RefuelService refuelService;
    public CarStatsService(CarService carService, RefuelService refuelService) {
        this.carService = carService;
        this.refuelService = refuelService;
    }

    public CarStats getStats(Long carId, LocalDateTime startDate, LocalDateTime endDate) {
        CarStats stats = new CarStats();
        setValues(carId, stats, startDate, endDate);
        return stats;
    }

    private void setValues(Long carId, CarStats stats, LocalDateTime startDate, LocalDateTime endDate) {
        List<Refuel> refuels =  refuelService.getAllByCarIdAndRefuelDateBetween(carId, startDate, endDate);
        stats.setTotalPrice(refuels.stream().mapToDouble(refuel -> refuel.getPriceForLiter()*refuel.getVolume()).sum());
        stats.setTotalRefuels(refuels.size());
        stats.setTotalVolume(refuels.stream().mapToDouble(refuel -> refuel.getVolume()).sum());

    }

}
