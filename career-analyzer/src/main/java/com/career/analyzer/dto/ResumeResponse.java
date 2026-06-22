package com.career.analyzer.dto;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class ResumeResponse {

    private Long id;
    private String fileName;
    private String fileType;
    private String extractedText;
    private List<Map<String, Object>> recommendations;
    private List<String> roadmapSteps;
    private String domain;
    private Map<String,List<String>>
            categorizedSkills;
    private List<String> missingSkills;
    // getters and setters
}
