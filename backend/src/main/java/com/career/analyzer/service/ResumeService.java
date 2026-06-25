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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
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
    @Autowired
private OcrService ocrService;

    public ResumeResponse uploadResume(
            Long userId,
            MultipartFile file) throws Exception {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Step 1: Extract text
        // Step 1: Extract text using PDFBox

String extractedText =
        ResumeTextExtractor.extractText(
                file.getInputStream());

System.out.println(
        "PDF Text Length = "
                + extractedText.length());


// OCR fallback for image resumes

if(extractedText == null
        || extractedText.trim().length() < 50){

    System.out.println(
            "Image Resume Detected -> Running OCR");

    PDDocument document =
            PDDocument.load(
                    file.getInputStream());

    PDFRenderer renderer =
            new PDFRenderer(document);

    BufferedImage image =
            renderer.renderImageWithDPI(
                    0,
                    100);

    File tempImage =
            File.createTempFile(
                    "resume",
                    ".png");

    ImageIO.write(
            image,
            "png",
            tempImage);

    extractedText =
            ocrService.extractText(
                    tempImage);

    System.out.println(
            "OCR Text Length = "
                    + extractedText.length());

    document.close();
}

        // Step 2: Extract skills
        List<String> skills =
                skillExtractor.extractSkills(
                        extractedText);
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

        resume.setData(
                file.getBytes());
                if(extractedText.length() > 10000){
    extractedText = extractedText.substring(0,10000);
}

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