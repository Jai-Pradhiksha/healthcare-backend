package com.vts.healthcare;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HealthcareRepository extends JpaRepository<HealthcareUser, Long> {
    @Query("SELECT u FROM HealthcareUser u WHERE u.email = ?1")
    Optional<HealthcareUser> findUserByEmail(String email);
    boolean existsByEmail(String email);
}
