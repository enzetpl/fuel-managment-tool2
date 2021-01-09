package pl.pussy.fuelmanagmenttool2.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "username must not be null")
    @Column(unique = true)
    @Size(min = 5, message = "minimum username length is 5")
    private String username;
    @Email(message = "enter valid email adress")
    @NotBlank(message = "email must not be null")
    private String email;
    @NotBlank(message = "password must not be null")
    @Size(min = 5, message = "minimum password length is 5")
    private String password;
    @Enumerated(EnumType.STRING)
    private ERole role;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Car> cars;
}
