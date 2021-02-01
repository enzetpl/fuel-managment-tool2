package pl.pussy.fuelmanagmenttool2.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pussy.fuelmanagmenttool2.exceptions.MessageResponse;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
    @GetMapping

    public ResponseEntity<?> authenticate() {
        return ResponseEntity.ok(new MessageResponse("You are authenticated"));
    }
}
