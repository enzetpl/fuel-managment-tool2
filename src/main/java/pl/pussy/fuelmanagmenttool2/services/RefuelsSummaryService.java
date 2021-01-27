package pl.pussy.fuelmanagmenttool2.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import pl.pussy.fuelmanagmenttool2.exceptions.NoResourceException;
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

    private static final int PRECISION = 2;

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
        if(refuels.isEmpty())
            throw new NoResourceException("not any data for this date");
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
        stats.setTotalPrice(setPrecision(totalPrice, PRECISION));
        stats.setTotalRefuels(refuels.size());
        stats.setTotalVolume
                (setPrecision(refuels.stream().mapToDouble(Refuel::getVolume).sum(), PRECISION));
        stats.setAvgPricePerLiter
                (setPrecision(stats.getTotalPrice() / stats.getTotalVolume(), PRECISION));
        stats.setMaxPricePerLiter
                (setPrecision(refuels.stream().mapToDouble(Refuel::getPriceForLiter).max().orElse(-1), PRECISION));
        stats.setMinPricePerLiter
                (setPrecision(refuels.stream().mapToDouble(Refuel::getPriceForLiter).min().orElse(-1), PRECISION));

    }

    private double setPrecision(double value, int precision) {
        return BigDecimal.valueOf(value).setScale(precision, RoundingMode.HALF_UP).doubleValue();
    }
}
