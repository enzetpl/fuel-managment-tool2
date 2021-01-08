package pl.pussy.fuelmanagmenttool2.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ERole role;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Car> cars;
}
