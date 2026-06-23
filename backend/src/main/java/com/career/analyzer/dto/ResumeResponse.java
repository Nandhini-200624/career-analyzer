package com.career.analyzer.dto;

import java.util.List;
import java.util.Map;

public class ResumeResponse {

    private Long id;
    private String fileName;
    private String fileType;
    private String extractedText;
    private List<Map<String, Object>> recommendations;
    private List<String> roadmapSteps;
    private String domain;
    private Map<String, List<String>> categorizedSkills;
    private List<String> missingSkills;

    public ResumeResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }

    public List<Map<String, Object>> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(
            List<Map<String, Object>> recommendations) {
        this.recommendations = recommendations;
    }

    public List<String> getRoadmapSteps() {
        return roadmapSteps;
    }

    public void setRoadmapSteps(List<String> roadmapSteps) {
        this.roadmapSteps = roadmapSteps;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Map<String, List<String>> getCategorizedSkills() {
        return categorizedSkills;
    }

    public void setCategorizedSkills(
            Map<String, List<String>> categorizedSkills) {
        this.categorizedSkills = categorizedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
}