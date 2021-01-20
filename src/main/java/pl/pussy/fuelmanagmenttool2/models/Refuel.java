package pl.pussy.fuelmanagmenttool2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "refuels")
public class Refuel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Min(value = 1)
    @Max(value = 1000)
    //@NotBlank
    private float volume;
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "20")
    //@NotBlank
    private float priceForLiter;
    //@NotBlank
    private LocalDateTime refuelDate;
    private int carMileage;
    @ManyToOne
    @JsonIgnore
    private Car car;

}
