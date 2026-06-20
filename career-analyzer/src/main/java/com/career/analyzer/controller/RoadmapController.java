package com.career.analyzer.controller;

import com.career.analyzer.dto.CareerRoadmapResponse;
import com.career.analyzer.service.RoadmapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roadmap")
public class RoadmapController {

    @Autowired
    private RoadmapService roadmapService;

    @PostMapping
    public CareerRoadmapResponse getRoadmap(
            @RequestBody List<String> skills) {

        return roadmapService
                .generateRoadmap(skills);
    }
}
