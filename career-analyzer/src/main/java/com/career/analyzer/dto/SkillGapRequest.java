package com.career.analyzer.dto;

public class SkillGapRequest {

    private String currentSkills;
    private String careerGoal;

    public SkillGapRequest() {
    }

    public String getCurrentSkills() {
        return currentSkills;
    }

    public void setCurrentSkills(String currentSkills) {
        this.currentSkills = currentSkills;
    }

    public String getCareerGoal() {
        return careerGoal;
    }

    public void setCareerGoal(String careerGoal) {
        this.careerGoal = careerGoal;
    }
}