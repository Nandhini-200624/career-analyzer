package com.career.analyzer.dto;

import java.util.List;

public class CareerRoadmapResponse {

    private String targetRole;

    private List<String> currentSkills;

    private List<String> missingSkills;

    private List<String> roadmapSteps;

    public String getTargetRole() {
        return targetRole;
    }

    public void setTargetRole(String targetRole) {
        this.targetRole = targetRole;
    }

    public List<String> getCurrentSkills() {
        return currentSkills;
    }

    public void setCurrentSkills(List<String> currentSkills) {
        this.currentSkills = currentSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public List<String> getRoadmapSteps() {
        return roadmapSteps;
    }

    public void setRoadmapSteps(List<String> roadmapSteps) {
        this.roadmapSteps = roadmapSteps;
    }
}