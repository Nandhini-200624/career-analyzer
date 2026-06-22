package com.career.analyzer.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillCategorizationService {

    private final SkillDatabaseService
            skillDatabaseService;

    public SkillCategorizationService(
            SkillDatabaseService skillDatabaseService) {

        this.skillDatabaseService =
                skillDatabaseService;
    }

    public Map<String,List<String>>
    categorizeSkills(
            List<String> skills) {

        Map<String,List<String>>
                categorizedSkills =
                new LinkedHashMap<>();

        Map<String,String>
                categoryMap =
                skillDatabaseService
                        .getCategoryMap();

        for(String skill : skills){

            String category =
                    categoryMap.getOrDefault(
                            skill,
                            "Other"
                    );

            categorizedSkills
                    .computeIfAbsent(
                            category,
                            k -> new ArrayList<>()
                    )
                    .add(skill);
        }

        return categorizedSkills;
    }
}