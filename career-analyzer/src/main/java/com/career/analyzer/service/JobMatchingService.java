package com.career.analyzer.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobMatchingService {

    public String matchJobRole(List<String> skills) {

        if (skills.contains("Machine Learning")) {
            return "Data Scientist";
        }

        if (skills.contains("Spring Boot") || skills.contains("Java")) {
            return "Backend Developer";
        }

        if (skills.contains("React") || skills.contains("JavaScript")) {
            return "Frontend Developer";
        }

        if (skills.contains("AWS") || skills.contains("Docker")) {
            return "Cloud Engineer";
        }

        return "Software Developer";
    }
}