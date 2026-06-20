package com.career.analyzer.controller;

import com.career.analyzer.service.JobScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/score")
public class JobScoreController {

    @Autowired
    private JobScoreService jobScoreService;

    @PostMapping("/match")
    public Map<String, Object> getScore(@RequestBody List<String> skills) {
        return jobScoreService.calculateBestMatch(skills);
    }
}