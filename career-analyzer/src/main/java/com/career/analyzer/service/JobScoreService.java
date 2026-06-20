package com.career.analyzer.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobScoreService {

    private final Map<String, List<String>> jobSkills = new HashMap<>();

    public JobScoreService() {

        jobSkills.put("Backend Developer",
                Arrays.asList("Java", "Spring Boot", "SQL", "Hibernate"));

        jobSkills.put("Frontend Developer",
                Arrays.asList("HTML", "CSS", "JavaScript", "React"));

        jobSkills.put("Data Scientist",
                Arrays.asList("Python", "Machine Learning", "Pandas", "SQL"));

        jobSkills.put("Cloud Engineer",
                Arrays.asList("AWS", "Docker", "Kubernetes", "Linux"));
    }

    public Map<String, Object> calculateBestMatch(List<String> userSkills) {

        String bestRole = "";
        double bestScore = 0;

        for (String role : jobSkills.keySet()) {

            List<String> requiredSkills = jobSkills.get(role);

            int matchCount = 0;

            for (String skill : userSkills) {
                if (requiredSkills.contains(skill)) {
                    matchCount++;
                }
            }

            double score = (double) matchCount / requiredSkills.size() * 100;

            if (score > bestScore) {
                bestScore = score;
                bestRole = role;
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("bestRole", bestRole);
        result.put("matchScore", bestScore);

        return result;
    }
}