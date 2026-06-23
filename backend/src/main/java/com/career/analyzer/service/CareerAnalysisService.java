package com.career.analyzer.service;

import com.career.analyzer.dto.*;
import org.springframework.stereotype.Service;

@Service
public class CareerAnalysisService {

    private final SkillGapService skillGapService;
    private final LearningPathService learningPathService;

    public CareerAnalysisService(
            SkillGapService skillGapService,
            LearningPathService learningPathService) {

        this.skillGapService = skillGapService;
        this.learningPathService = learningPathService;
    }

    public CareerAnalysisResponse analyze(
            CareerAnalysisRequest request) {

        SkillGapResponse skillGap =
                skillGapService.analyzeSkills(
                        request.getCurrentSkills(),
                        request.getCareerGoal());

        LearningPathResponse path =
                learningPathService.generatePath(
                        request.getCareerGoal());

        return new CareerAnalysisResponse(
                request.getCareerGoal(),
                skillGap.getMissingSkills(),
                path.getCourses()
        );
    }
}