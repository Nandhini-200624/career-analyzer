package com.career.analyzer.controller;

import com.career.analyzer.dto.ResumeResponse;
import com.career.analyzer.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/upload/{userId}")
    public ResumeResponse uploadResume(@PathVariable Long userId,
                                 @RequestParam("file") MultipartFile file) throws Exception {

        return resumeService.uploadResume(userId, file);
    }
}