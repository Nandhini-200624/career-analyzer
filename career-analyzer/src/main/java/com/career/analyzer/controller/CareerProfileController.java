package com.career.analyzer.controller;

import com.career.analyzer.entity.CareerProfile;
import com.career.analyzer.service.CareerProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class CareerProfileController {
    private final CareerProfileService service;

    public CareerProfileController(CareerProfileService service) {
        this.service = service;
    }
    @PostMapping
    public CareerProfile createProfile(
            @RequestBody CareerProfile profile) {
        System.out.println(profile.getEducation());
        System.out.println(profile.getCurrentSkills());
        return service.saveProfile(profile);
    }
    @GetMapping
    public List<CareerProfile> getAllProfiles() {

        return service.getAllProfiles();
    }
    @GetMapping("/{id}")
    public CareerProfile getProfileById(
            @PathVariable Long id) {

        return service.getProfileById(id);
    }
}