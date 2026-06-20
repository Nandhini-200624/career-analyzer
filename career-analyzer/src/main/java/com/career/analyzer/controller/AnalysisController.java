package com.career.analyzer.controller;

import com.career.analyzer.service.JobMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analyze")
public class AnalysisController {

    @Autowired
    private JobMatchingService jobMatchingService;

    @PostMapping("/job-role")
    public String getJobRole(@RequestBody List<String> skills) {
        return jobMatchingService.matchJobRole(skills);
    }
}