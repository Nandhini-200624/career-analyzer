package com.career.analyzer.controller;

import com.career.analyzer.dto.ResumeResponse;
import com.career.analyzer.dto.ManualSkillRequest;
import com.career.analyzer.service.ManualSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manual")
public class ManualSkillController {

    @Autowired
    private ManualSkillService manualSkillService;

    @PostMapping("/skills/{userId}")
    public ResumeResponse analyzeSkills(
            @PathVariable Long userId,
            @RequestBody ManualSkillRequest request) {

       return manualSkillService.analyzeSkills(
            userId,
            request.getSkills()
       );
}
}