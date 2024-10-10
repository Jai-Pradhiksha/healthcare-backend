package com.vts.healthcare;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class HealthcareUser {
    @Id
    @SequenceGenerator(
            name = "healthcareUserSequence",
            sequenceName = "healthcareUserSequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "healthcareUserSequence"
    )
    private Long userID;
    private String userName;
    private String email;
    private String password;
    private String role;
    private LocalDate dateOfBirth;

    public HealthcareUser() {
    }

    public HealthcareUser(String userName, String email, String password, String role, LocalDate dateOfBirth) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
