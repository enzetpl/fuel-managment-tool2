package pl.pussy.fuelmanagmenttool2.controllers;

import org.springframework.web.bind.annotation.*;
import pl.pussy.fuelmanagmenttool2.security.AuthenticationBean;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/auth")
public class AuthController {
    @GetMapping

    public AuthenticationBean authenticate() {
        System.out.println("AUTH CONTROLLERRRRR");
        return new AuthenticationBean("You are authenticated");
    }
}
