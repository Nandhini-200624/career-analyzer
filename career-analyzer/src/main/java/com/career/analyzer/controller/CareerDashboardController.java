package com.career.analyzer.controller;

import com.career.analyzer.entity.CareerProfile;
import com.career.analyzer.repository.CareerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class CareerDashboardController {

    @Autowired
    private CareerProfileRepository repo;

    @GetMapping("/{userId}")
    public CareerProfile getProfile(@PathVariable Long userId) {

        return repo.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Profile not found"));
    }
}