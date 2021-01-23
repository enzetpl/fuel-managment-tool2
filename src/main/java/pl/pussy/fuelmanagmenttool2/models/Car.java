package pl.pussy.fuelmanagmenttool2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "cars")
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "brand must not be null")
    private String brand;
    @NotBlank(message = "model must not be null")
    private String model;
    @Min(value = 1900)
    @Max(value = 2100)
    private Integer productionYear;
    @Size(min = 3, max = 9)
    private String plate;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @ManyToOne
    @JsonIgnore
    private User user;
    @JoinColumn(name = "car_id")
    @OneToMany(cascade = CascadeType.ALL)
    List<Refuel> refuels;
}
