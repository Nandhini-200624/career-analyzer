package com.career.analyzer.service;

import com.career.analyzer.dto.RecommendationResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CareerRecommendationService {

    public RecommendationResponse recommendCareer(
            String education,
            String skills,
            String domain) {

        List<String> careers = new ArrayList<>();

        String lowerSkills = skills.toLowerCase();

        if (lowerSkills.contains("java")) {
            careers.add("Java Developer");
            careers.add("Backend Developer");
        }

        if (lowerSkills.contains("sql")) {
            careers.add("Database Developer");
        }

        if (domain.equalsIgnoreCase("AI")) {
            careers.add("Machine Learning Engineer");
            careers.add("Data Scientist");
        }

        if (domain.equalsIgnoreCase("Software Development")) {
            careers.add("Software Engineer");
        }

        return new RecommendationResponse(careers);
    }
}