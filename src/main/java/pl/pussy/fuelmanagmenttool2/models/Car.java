package pl.pussy.fuelmanagmenttool2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;
    private String model;
    private int productionYear;
    private String plate;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @ManyToOne
    @JsonIgnore
    private User user;
}
