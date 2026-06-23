package com.career.analyzer.controller;

import com.career.analyzer.dto.RecommendationRequest;
import com.career.analyzer.dto.RecommendationResponse;
import com.career.analyzer.service.CareerRecommendationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommend")
public class CareerRecommendationController {

    private final CareerRecommendationService service;

    public CareerRecommendationController(
            CareerRecommendationService service) {

        this.service = service;
    }

    @PostMapping
    public RecommendationResponse recommend(
            @RequestBody RecommendationRequest request) {

        return service.recommendCareer(
                request.getEducation(),
                request.getSkills(),
                request.getDomain()
        );
    }
}