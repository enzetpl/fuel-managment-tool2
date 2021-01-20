package pl.pussy.fuelmanagmenttool2.services;

import org.springframework.stereotype.Service;
import pl.pussy.fuelmanagmenttool2.models.RefuelsSummary;
import pl.pussy.fuelmanagmenttool2.models.Refuel;
import pl.pussy.fuelmanagmenttool2.security.SecurityUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RefuelsSummaryService {

    private final CarService carService;
    private final RefuelService refuelService;
    public RefuelsSummaryService(CarService carService, RefuelService refuelService) {
        this.carService = carService;
        this.refuelService = refuelService;
    }

    public RefuelsSummary getStatsForCar(Long carId, LocalDate startDate, LocalDate endDate) {
        RefuelsSummary stats = new RefuelsSummary();
        setValuesForCar(carId, stats, startDate, endDate);
        return stats;
    }

    private void setValuesForCar(Long carId, RefuelsSummary stats, LocalDate startDate, LocalDate endDate) {
        List<Refuel> refuels =  refuelService.getAllByCarIdAndRefuelDateBetween(carId, startDate, endDate);
        setValues(stats, refuels);

    }

    public RefuelsSummary getStatsForUser(LocalDate startDate, LocalDate endDate) {
        RefuelsSummary stats = new RefuelsSummary();
        setValuesForUser(stats, startDate, endDate);
        return stats;
    }

    private void setValuesForUser(RefuelsSummary stats, LocalDate startDate, LocalDate endDate) {
        String username = SecurityUtils.getCurrentUserUsername();
        List<Refuel> refuels =  refuelService.getAllByCarUserUsernameAndRefuelDateBetween(username, startDate, endDate);
        setValues(stats, refuels);
    }

    private void setValues(RefuelsSummary stats, List<Refuel> refuels) {
        double totalPrice = refuels.stream().mapToDouble(refuel -> refuel.getPriceForLiter() * refuel.getVolume()).sum();
        stats.setTotalPrice(BigDecimal.valueOf(totalPrice).setScale(2, RoundingMode.HALF_UP).doubleValue());
        stats.setTotalRefuels(refuels.size());
        stats.setTotalVolume(refuels.stream().mapToDouble(Refuel::getVolume).sum());
    }
}
