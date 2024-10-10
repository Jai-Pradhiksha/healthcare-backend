package com.vts.healthcare;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HealthcareService {

    private final HealthcareRepository healthcareRepository;

    @Autowired
    public HealthcareService(HealthcareRepository healthcareRepository) {
        this.healthcareRepository = healthcareRepository;
    }

    public List<HealthcareUser> getAllUsers() {
        return healthcareRepository.findAll();
    }

    public void addNewUser(HealthcareUser user) {
        if (user.getDateOfBirth() == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Date of birth is required");
        }

        healthcareRepository.save(user);
    }

    public HealthcareUser getUserById(Long userID) {
        return healthcareRepository.findById(userID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userID + " does not exist"));
    }

    @Transactional
    public void updateUser(Long userID, HealthcareUser updatedUser) {
        HealthcareUser user = healthcareRepository.findById(userID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userID + " does not exist"));

        if (updatedUser.getUserName() != null && !updatedUser.getUserName().isEmpty()) {
            user.setUserName(updatedUser.getUserName());
        }

        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
            if (healthcareRepository.existsByEmail(updatedUser.getEmail())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
            }
            user.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(updatedUser.getPassword());
        }

        if (updatedUser.getRole() != null && !updatedUser.getRole().isEmpty()) {
            user.setRole(updatedUser.getRole());
        }

        if (updatedUser.getDateOfBirth() != null) {
            user.setDateOfBirth(updatedUser.getDateOfBirth());
        }
    }

    public void deleteUser(Long userID) {
        if (!healthcareRepository.existsById(userID)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userID + " does not exist");
        }
        healthcareRepository.deleteById(userID);
    }

    public HealthcareUser getUserByEmail(String email) {
        return healthcareRepository.findUserByEmail(email)
                .orElse(null);
    }

    public HealthcareUser findUserByEmail(String email) {
        return healthcareRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "User not found with email: " + email));
    }
}
