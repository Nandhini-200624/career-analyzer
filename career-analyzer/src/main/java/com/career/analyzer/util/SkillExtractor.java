package com.career.analyzer.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkillExtractor {

    private static final List<String> SKILLS = Arrays.asList(
            "Java", "Spring", "Spring Boot", "Hibernate",
            "Python", "Machine Learning", "SQL",
            "JavaScript", "React", "HTML", "CSS",
            "Docker", "AWS"
    );

    public static List<String> extractSkills(String text) {

        List<String> foundSkills = new ArrayList<>();

        if (text == null) return foundSkills;

        for (String skill : SKILLS) {
            if (text.toLowerCase().contains(skill.toLowerCase())) {
                foundSkills.add(skill);
            }
        }

        return foundSkills;
    }
}