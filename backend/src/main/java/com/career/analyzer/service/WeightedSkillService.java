package com.career.analyzer.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WeightedSkillService {

    private final Map<String, Map<String, Integer>> jobSkills = new HashMap<>();

    public WeightedSkillService() {

        Map<String, Integer> backend = new HashMap<>();
        backend.put("Java", 5);
        backend.put("Spring Boot", 5);
        backend.put("SQL", 3);
        backend.put("Hibernate", 2);

        Map<String, Integer> frontend = new HashMap<>();
        frontend.put("HTML", 5);
        frontend.put("CSS", 5);
        frontend.put("JavaScript", 5);
        frontend.put("React", 4);

        Map<String, Integer> data = new HashMap<>();
        data.put("Python", 5);
        data.put("Machine Learning", 5);
        data.put("SQL", 3);
        data.put("Pandas", 4);

        jobSkills.put("Backend Developer", backend);
        jobSkills.put("Frontend Developer", frontend);
        jobSkills.put("Data Scientist", data);
    }

    public List<Map<String, Object>> getTopJobs(List<String> userSkills) {

        List<Map<String, Object>> results = new ArrayList<>();

        for (String job : jobSkills.keySet()) {

            Map<String, Integer> skills = jobSkills.get(job);

            int totalWeight = 0;
            int matchedWeight = 0;

            for (String skill : skills.keySet()) {

                totalWeight += skills.get(skill);

                if (userSkills.contains(skill)) {
                    matchedWeight += skills.get(skill);
                }
            }

            double score = (double) matchedWeight / totalWeight * 100;

            Map<String, Object> jobResult = new HashMap<>();
            jobResult.put("role", job);
            jobResult.put("score", score);

            results.add(jobResult);
        }

        results.sort((a, b) ->
                Double.compare((double) b.get("score"), (double) a.get("score")));

        return results;
    }
}