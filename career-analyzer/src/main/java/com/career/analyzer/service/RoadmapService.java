package com.career.analyzer.service;

import com.career.analyzer.dto.CareerRoadmapResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoadmapService {

    public CareerRoadmapResponse generateRoadmap(
            String role,
            List<String> missingSkills) {

        CareerRoadmapResponse response =
                new CareerRoadmapResponse();

        response.setTargetRole(role);

        response.setMissingSkills(
                missingSkills
        );

        List<String> roadmapSteps =
                new ArrayList<>();

        // Learn missing skills first
        for(String skill : missingSkills) {

            roadmapSteps.add(
                    "Learn " + skill
            );
        }

        // Role-specific roadmap
        if(role.equalsIgnoreCase(
                "Frontend Developer")) {

            roadmapSteps.add(
                    "Build React Projects");

            roadmapSteps.add(
                    "Create Frontend Portfolio");

            roadmapSteps.add(
                    "Deploy Frontend Applications");
        }

        else if(role.equalsIgnoreCase(
                "Backend Developer")) {

            roadmapSteps.add(
                    "Build REST APIs");

            roadmapSteps.add(
                    "Work with Databases");

            roadmapSteps.add(
                    "Deploy Backend Services");
        }

        else if(role.equalsIgnoreCase(
                "Data Scientist")) {

            roadmapSteps.add(
                    "Build Machine Learning Models");

            roadmapSteps.add(
                    "Create Data Science Projects");

            roadmapSteps.add(
                    "Deploy ML Solutions");
        }

        response.setRoadmapSteps(
                roadmapSteps
        );

        return response;
    }

}
