package pl.pussy.fuelmanagmenttool2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.OptionalInt;

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
    @Range(min = 2000, max = 2022)
    @Nullable
    private Integer productionYear;
    private String plate;
    @Enumerated(EnumType.STRING)
    //@NotBlank
    private FuelType fuelType;
    @ManyToOne
    @JsonIgnore
    private User user;
}
