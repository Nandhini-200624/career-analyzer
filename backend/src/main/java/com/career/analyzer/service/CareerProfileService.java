package com.career.analyzer.service;

import com.career.analyzer.entity.CareerProfile;
import com.career.analyzer.entity.User;
import com.career.analyzer.repository.CareerProfileRepository;
import com.career.analyzer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CareerProfileService {

    private final UserRepository userRepository;
    private final CareerProfileRepository repository;

    public CareerProfileService(
            CareerProfileRepository repository,
            UserRepository userRepository) {

        this.repository = repository;
        this.userRepository = userRepository;
    }


    // Step 8: Save Profile
    public CareerProfile saveProfile(CareerProfile profile) {
        return repository.save(profile);
    }

    // Step 9: Get All Profiles
    public List<CareerProfile> getAllProfiles() {
        return repository.findAll();
    }

    // Step 10: Get Profile By ID
    public CareerProfile getProfileById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public void updateProfile(Long userId, List<String> skills) {
        System.out.println("updateProfile called");
        System.out.println("userRepository = " + userRepository);
        User user = userRepository.findById(userId)
                .orElseThrow();

        CareerProfile profile = user.getCareerProfile();

        if (profile == null) {
            profile = new CareerProfile();
        }

        profile.setCurrentSkills(
                String.join(", ", skills)
        );

        if (skills.contains("Java")
                || skills.contains("Spring Boot")) {

            profile.setInterestedDomain(
                    "Backend Development");

            profile.setCareerGoal(
                    "Backend Developer");
        }

        if (skills.contains("Python")
                || skills.contains("Machine Learning")) {

            profile.setInterestedDomain(
                    "AI/ML");

            profile.setCareerGoal(
                    "Data Scientist");
        }
        profile.setExperienceLevel("Fresher");
        CareerProfile savedProfile = repository.save(profile);

        user.setCareerProfile(savedProfile);

        userRepository.save(user);

    }
}
