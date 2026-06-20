package com.career.analyzer.dto;

import java.util.List;

public class CareerAnalysisResponse {

    private String recommendedCareer;
    private List<String> missingSkills;
    private List<String> learningCourses;

    public CareerAnalysisResponse() {
    }

    public CareerAnalysisResponse(
            String recommendedCareer,
            List<String> missingSkills,
            List<String> learningCourses) {

        this.recommendedCareer = recommendedCareer;
        this.missingSkills = missingSkills;
        this.learningCourses = learningCourses;
    }

    public String getRecommendedCareer() {
        return recommendedCareer;
    }

    public void setRecommendedCareer(String recommendedCareer) {
        this.recommendedCareer = recommendedCareer;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public List<String> getLearningCourses() {
        return learningCourses;
    }

    public void setLearningCourses(List<String> learningCourses) {
        this.learningCourses = learningCourses;
    }
}