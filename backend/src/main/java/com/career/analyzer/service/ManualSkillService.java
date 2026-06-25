package com.career.analyzer.service;

import com.career.analyzer.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManualSkillService {

    @Autowired
    private WeightedSkillService weightedSkillService;

    @Autowired
    private SkillGapService skillGapService;

    @Autowired
    private RoadmapService roadmapService;

    @Autowired
    private DomainDetectionService domainDetectionService;

    @Autowired
    private SkillCategorizationService skillCategorizationService;

    public ResumeResponse analyzeSkills(
            Long userId,
            String skillsText) {

        List<String> skills =
                List.of(skillsText.split(","));

        List<Map<String,Object>>
                recommendations =
                weightedSkillService
                        .getTopJobs(skills);

        String bestRole =
                recommendations.get(0)
                        .get("role")
                        .toString();

        SkillGapResponse gap =
                skillGapService.analyzeSkills(
                        String.join(",", skills),
                        bestRole
                );

        CareerRoadmapResponse roadmap =
                roadmapService.generateRoadmap(
                        bestRole,
                        gap.getMissingSkills()
                );

        ResumeResponse response =
                new ResumeResponse();

        response.setRecommendations(
                recommendations);

        response.setRoadmapSteps(
                roadmap.getRoadmapSteps());

        response.setMissingSkills(
                gap.getMissingSkills());

        response.setDomain(
                domainDetectionService
                        .detectDomain(skills));

        response.setCategorizedSkills(
                skillCategorizationService
                        .categorizeSkills(skills));

        response.setExtractedText(
                "Skills: " + skills);

        return response;
    }
}