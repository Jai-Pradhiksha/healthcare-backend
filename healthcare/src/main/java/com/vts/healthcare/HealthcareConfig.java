package com.vts.healthcare;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class HealthcareConfig {

    @Bean
    CommandLineRunner commandLineRunner(HealthcareRepository healthcareRepository) {
        return args -> {
            if (healthcareRepository.count() == 0) {
                // Create default users
                HealthcareUser patient = new HealthcareUser(
                        "Patient One",
                        "patient1@healthcare.com",
                        "ppassword123",
                        "PATIENT",
                        LocalDate.of(1995, Month.JANUARY, 1)
                );

                HealthcareUser doctor = new HealthcareUser(
                        "Doctor One",
                        "doctor1@healthcare.com",
                        "dpassword123",
                        "DOCTOR",
                        LocalDate.of(1980, Month.JUNE, 15)
                );

                HealthcareUser admin = new HealthcareUser(
                        "Admin User One",
                        "admin1@healthcare.com",
                        "apassword123",
                        "ADMIN",
                        LocalDate.of(1975, Month.MARCH, 30)
                );

                healthcareRepository.saveAll(List.of(patient, doctor, admin));
            }
        };
    }
}
