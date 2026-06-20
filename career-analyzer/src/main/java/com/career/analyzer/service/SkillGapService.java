package com.career.analyzer.service;

import com.career.analyzer.dto.SkillGapResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SkillGapService {

    private final Map<String, List<String>> careerSkills = Map.of(

            "Machine Learning Engineer",
            List.of("Python",
                    "Machine Learning",
                    "Statistics",
                    "TensorFlow"),

            "Backend Developer",
            List.of("Java",
                    "Spring Boot",
                    "MySQL",
                    "REST API"),

            "Data Scientist",
            List.of("Python",
                    "Pandas",
                    "Machine Learning",
                    "Statistics")
    );

    public SkillGapResponse analyzeSkills(
            String currentSkills,
            String careerGoal) {

        List<String> userSkills =
                Arrays.stream(currentSkills.split(","))
                        .map(String::trim)
                        .toList();

        List<String> requiredSkills =
                careerSkills.getOrDefault(
                        careerGoal,
                        List.of());

        List<String> missingSkills =
                requiredSkills.stream()
                        .filter(skill ->
                                !userSkills.contains(skill))
                        .toList();

        return new SkillGapResponse(
                userSkills,
                requiredSkills,
                missingSkills
        );
    }
}