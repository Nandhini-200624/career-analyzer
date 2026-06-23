package com.career.analyzer.controller;

import com.career.analyzer.dto.CareerAnalysisRequest;
import com.career.analyzer.dto.CareerAnalysisResponse;
import com.career.analyzer.service.CareerAnalysisService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analyze")
public class CareerAnalysisController {

    private final CareerAnalysisService service;

    public CareerAnalysisController(
            CareerAnalysisService service) {

        this.service = service;
    }

    @PostMapping
    public CareerAnalysisResponse analyze(
            @RequestBody CareerAnalysisRequest request) {

        return service.analyze(request);
    }
}