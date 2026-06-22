package com.career.analyzer.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class SkillDatabaseService {

    private final List<String> skills =
            new ArrayList<>();

    private final Map<String,String>
            categoryMap =
            new HashMap<>();

    @PostConstruct
    public void loadSkills() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    getClass()
                                            .getResourceAsStream(
                                                    "/skill_master.csv"
                                            )
                            )
                    );

            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] parts =
                        line.split(",");

                if(parts.length >= 2){

                    String category =
                            parts[0].trim();

                    String skill =
                            parts[1].trim();

                    skills.add(skill);

                    categoryMap.put(
                            skill,
                            category
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<String> getSkills() {

        return skills;
    }

    public Map<String,String>
    getCategoryMap() {

        return categoryMap;
    }
}