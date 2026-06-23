package com.career.analyzer.dto;

import java.util.List;

public class SkillGapResponse {

    private List<String> currentSkills;
    private List<String> requiredSkills;
    private List<String> missingSkills;

    public SkillGapResponse() {
    }

    public SkillGapResponse(
            List<String> currentSkills,
            List<String> requiredSkills,
            List<String> missingSkills) {

        this.currentSkills = currentSkills;
        this.requiredSkills = requiredSkills;
        this.missingSkills = missingSkills;
    }

    public List<String> getCurrentSkills() {
        return currentSkills;
    }

    public void setCurrentSkills(List<String> currentSkills) {
        this.currentSkills = currentSkills;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
}