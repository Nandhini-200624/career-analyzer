package com.career.analyzer.dto;

import java.util.List;

public class LearningPathResponse {

    private String career;
    private List<String> courses;
    private List<String> projects;
    private List<String> certifications;

    public LearningPathResponse() {
    }

    public LearningPathResponse(
            String career,
            List<String> courses,
            List<String> projects,
            List<String> certifications) {

        this.career = career;
        this.courses = courses;
        this.projects = projects;
        this.certifications = certifications;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
    }
}