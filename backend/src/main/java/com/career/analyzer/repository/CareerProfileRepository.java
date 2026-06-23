package com.career.analyzer.repository;

import com.career.analyzer.entity.CareerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CareerProfileRepository extends JpaRepository<CareerProfile, Long> {

    Optional<CareerProfile> findByUserId(Long userId);
}
