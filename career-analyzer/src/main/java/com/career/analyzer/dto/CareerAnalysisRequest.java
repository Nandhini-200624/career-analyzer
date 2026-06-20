package com.career.analyzer.dto;

public class CareerAnalysisRequest {

    private String education;
    private String currentSkills;
    private String interestedDomain;
    private String careerGoal;

    public CareerAnalysisRequest() {
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCurrentSkills() {
        return currentSkills;
    }

    public void setCurrentSkills(String currentSkills) {
        this.currentSkills = currentSkills;
    }

    public String getInterestedDomain() {
        return interestedDomain;
    }

    public void setInterestedDomain(String interestedDomain) {
        this.interestedDomain = interestedDomain;
    }

    public String getCareerGoal() {
        return careerGoal;
    }

    public void setCareerGoal(String careerGoal) {
        this.careerGoal = careerGoal;
    }
}