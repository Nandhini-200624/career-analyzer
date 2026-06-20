package com.career.analyzer.controller;

import com.career.analyzer.dto.SkillGapRequest;
import com.career.analyzer.dto.SkillGapResponse;
import com.career.analyzer.service.SkillGapService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skill-gap")
public class SkillGapController {

    private final SkillGapService service;

    public SkillGapController(SkillGapService service) {
        this.service = service;
    }

    @PostMapping
    public SkillGapResponse analyze(
            @RequestBody SkillGapRequest request) {

        return service.analyzeSkills(
                request.getCurrentSkills(),
                request.getCareerGoal()
        );
    }
}