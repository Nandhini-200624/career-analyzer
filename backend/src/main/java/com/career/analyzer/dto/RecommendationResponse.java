package com.career.analyzer.dto;

import java.util.List;

public class RecommendationResponse {

    private List<String> recommendedCareers;

    public RecommendationResponse() {
    }

    public RecommendationResponse(List<String> recommendedCareers) {
        this.recommendedCareers = recommendedCareers;
    }

    public List<String> getRecommendedCareers() {
        return recommendedCareers;
    }

    public void setRecommendedCareers(List<String> recommendedCareers) {
        this.recommendedCareers = recommendedCareers;
    }
}
