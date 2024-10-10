package com.vts.healthcare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/healthcare")
public class HealthcareController {
    private final HealthcareService healthcareService;

    @Autowired
    public HealthcareController(HealthcareService healthcareService) {
        this.healthcareService = healthcareService;
    }

    @GetMapping("/")
    public List<HealthcareUser> getUsers() {
        return healthcareService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public HealthcareUser getUserById(@PathVariable("id") Long userID) {
        return healthcareService.getUserById(userID);
    }

    @PutMapping(path = "/{id}")
    public void updateUser(@PathVariable("id") Long userID, @RequestBody HealthcareUser updatedUser) {
        healthcareService.updateUser(userID, updatedUser);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable("id") Long userID) {
        healthcareService.deleteUser(userID);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody HealthcareUser user) {
        HealthcareUser existingUser = healthcareService.getUserByEmail(user.getEmail());

        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "No account found with this email. Create a new one!"));
        }

        if (existingUser.getPassword().equals(user.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("user_name", existingUser.getUserName());
            response.put("role", existingUser.getRole());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Email or password is wrong. Please check."));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody HealthcareUser user) {
        try {
            if (healthcareService.getUserByEmail(user.getEmail()) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("This email is already registered, Try logging in");
            }
            healthcareService.addNewUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Oops! Registration failed due to an unexpected error");
        }
    }




}



