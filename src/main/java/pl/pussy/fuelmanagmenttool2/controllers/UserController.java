package pl.pussy.fuelmanagmenttool2.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.pussy.fuelmanagmenttool2.exceptions.ErrorResponse;
import pl.pussy.fuelmanagmenttool2.exceptions.MessageResponse;
import pl.pussy.fuelmanagmenttool2.models.User;
import pl.pussy.fuelmanagmenttool2.services.UserService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        if(userService.existsByUsername(user.getUsername())) {
            ErrorResponse.ErrorDetails error = new ErrorResponse.ErrorDetails();
            error.setMessage("username already taken!");
            error.setFieldName("username");
            ErrorResponse response = new ErrorResponse();
            response.setErrors(Collections.singletonList(error));
            return ResponseEntity.badRequest().body(response);
        }
        if(userService.existsByEmail(user.getEmail())) {
            ErrorResponse.ErrorDetails error = new ErrorResponse.ErrorDetails();
            error.setMessage("email already taken!");
            error.setFieldName("email");
            ErrorResponse response = new ErrorResponse();
            response.setErrors(Collections.singletonList(error));
            return ResponseEntity.badRequest().body(response);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        return ResponseEntity.ok(new MessageResponse("User regitered successfully"));
    }
}
