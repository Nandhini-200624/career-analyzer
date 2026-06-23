package com.career.analyzer.util;

import com.career.analyzer.service.SkillDatabaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillExtractor {

    private final SkillDatabaseService skillDatabaseService;

    public SkillExtractor(
            SkillDatabaseService skillDatabaseService) {

        this.skillDatabaseService =
                skillDatabaseService;
    }

    public List<String> extractSkills(
            String text) {

        List<String> foundSkills =
                new ArrayList<>();

        if (text == null)
            return foundSkills;

        String resumeText =
                text.toLowerCase();

        for (String skill :
                skillDatabaseService.getSkills()) {

            if (resumeText.contains(
                    skill.toLowerCase().trim())) {

                foundSkills.add(skill);
            }
        }

        return foundSkills
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }
}