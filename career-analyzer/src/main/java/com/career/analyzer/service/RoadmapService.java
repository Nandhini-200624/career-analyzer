package com.career.analyzer.service;


import com.career.analyzer.dto.CareerRoadmapResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadmapService {

    public CareerRoadmapResponse generateRoadmap(
            List<String> skills) {

        CareerRoadmapResponse response =
                new CareerRoadmapResponse();

        response.setCurrentSkills(skills);

        if(skills.contains("Java")) {

            response.setTargetRole(
                    "Backend Developer");

            response.setMissingSkills(
                    List.of(
                            "Spring Boot",
                            "REST API",
                            "Docker"
                    ));

            response.setRoadmapSteps(
                    List.of(
                            "Learn Spring Boot",
                            "Build REST APIs",
                            "Learn MySQL Integration",
                            "Learn Docker",
                            "Deploy Application"
                    ));
        }

        return response;
    }
}