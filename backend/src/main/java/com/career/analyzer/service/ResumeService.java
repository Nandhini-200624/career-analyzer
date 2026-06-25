package com.career.analyzer.service;

import com.career.analyzer.dto.CareerRoadmapResponse;
import com.career.analyzer.dto.ResumeResponse;
import com.career.analyzer.dto.SkillGapResponse;
import com.career.analyzer.entity.Resume;
import com.career.analyzer.entity.User;
import com.career.analyzer.repository.ResumeRepository;
import com.career.analyzer.repository.UserRepository;
import com.career.analyzer.util.ResumeTextExtractor;
import com.career.analyzer.util.SkillExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeightedSkillService weightedSkillService;

    @Autowired
    private CareerProfileService careerProfileService;

    @Autowired
    private RoadmapService roadmapService;

    @Autowired
    private SkillExtractor skillExtractor;
    @Autowired
    private DomainDetectionService
            domainDetectionService;
    @Autowired
    private SkillCategorizationService
            skillCategorizationService;
    @Autowired
    private SkillGapService skillGapService;

    public ResumeResponse uploadResume(
            Long userId,
            MultipartFile file) throws Exception {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Step 1: Extract text
        String extractedText = ResumeTextExtractor.extractText(file.getInputStream());
        if(extractedText == null|| extractedText.trim().length() < 50){
                throw new RuntimeException("MANUAL_SKILL_ENTRY_REQUIRED");
                }
        // Step 2: Extract skills
        List<String> skills =
                skillExtractor.extractSkills(
                        extractedText);
                        if(skills.isEmpty()){

    throw new RuntimeException(
            "MANUAL_SKILL_ENTRY_REQUIRED");
}
        Map<String,List<String>>
                categorizedSkills =
                skillCategorizationService
                        .categorizeSkills(skills);
        String domain =
                domainDetectionService
                        .detectDomain(skills);


        // Step 3: Update Career Profile
        careerProfileService.updateProfile(
                userId,
                skills);

        // Step 4: Check existing resume
        Optional<Resume> existing =
                resumeRepository.findByUserId(userId);

        System.out.println(
                "Resume Found = "
                        + existing.isPresent());

        Resume resume =
                existing.orElse(new Resume());

        System.out.println(
                "Resume ID = "
                        + resume.getId());

        // Step 5: Save Resume
        resume.setFileName(
                file.getOriginalFilename());

        resume.setFileType(
                file.getContentType());

        resume.setData(null);

        resume.setExtractedText(
                extractedText);

        resume.setUser(user);

        Resume saved =
                resumeRepository.save(resume);

        // Step 6: Job Recommendations
        List<Map<String, Object>>
                recommendations =
                weightedSkillService
                        .getTopJobs(skills);
                        if(recommendations.isEmpty()){

    throw new RuntimeException(
            "No matching jobs found");
}
        String bestRole =
                recommendations.get(0)
                        .get("role")
                        .toString();

        SkillGapResponse gapResponse =
                skillGapService.analyzeSkills(
                        String.join(",", skills),
                        bestRole
                );

        // Step 7: Roadmap Generation

        CareerRoadmapResponse roadmap =
                roadmapService.generateRoadmap(
                        bestRole,
                        gapResponse.getMissingSkills()
                );

        // Step 8: Build Response
        ResumeResponse response =
                new ResumeResponse();

        response.setId(
                saved.getId());

        response.setFileName(
                saved.getFileName());

        response.setFileType(
                saved.getFileType());

        response.setExtractedText(
                extractedText +
                        "\nSkills: " + skills);


        response.setRecommendations(
                recommendations);

        response.setRoadmapSteps(
                roadmap.getRoadmapSteps());
        response.setDomain(domain);
        response.setCategorizedSkills(
                categorizedSkills
        );

        response.setMissingSkills(
                gapResponse.getMissingSkills()
        );
        System.out.println(
                "Missing Skills = " +
                        gapResponse.getMissingSkills()
        );
        return response;
    }
}