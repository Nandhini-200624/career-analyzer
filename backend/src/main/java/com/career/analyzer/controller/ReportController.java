package com.career.analyzer.controller;

import com.career.analyzer.entity.CareerProfile;
import com.career.analyzer.repository.CareerProfileRepository;
import com.career.analyzer.service.PDFReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private PDFReportService pdfReportService;

    @Autowired
    private CareerProfileRepository profileRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<byte[]> downloadReport(
            @PathVariable Long userId) {

        CareerProfile profile =
                profileRepository
                        .findByUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Profile not found"));

        byte[] pdf =
                pdfReportService.generateReport(
                        profile.getCurrentSkills(),
                        profile.getCareerGoal(),
                        profile.getInterestedDomain().toString(),
                        profile.getEducation(),
                        profile.getExperienceLevel()
                );

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=Career_Analysis_Report.pdf"
                )
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
