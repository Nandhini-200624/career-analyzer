package com.career.analyzer.controller;

import com.career.analyzer.dto.LearningPathRequest;
import com.career.analyzer.dto.LearningPathResponse;
import com.career.analyzer.service.LearningPathService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/learning-path")
public class LearningPathController {

    private final LearningPathService service;

    public LearningPathController(
            LearningPathService service) {

        this.service = service;
    }

    @PostMapping
    public LearningPathResponse generate(
            @RequestBody LearningPathRequest request) {

        return service.generatePath(
                request.getCareer()
        );
    }
}