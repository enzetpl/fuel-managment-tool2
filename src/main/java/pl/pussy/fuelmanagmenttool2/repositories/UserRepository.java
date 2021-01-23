package pl.pussy.fuelmanagmenttool2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pussy.fuelmanagmenttool2.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
