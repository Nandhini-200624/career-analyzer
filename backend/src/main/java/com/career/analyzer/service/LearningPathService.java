package com.career.analyzer.service;

import com.career.analyzer.dto.LearningPathResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearningPathService {

    public LearningPathResponse generatePath(
            String career) {

        if (career.equalsIgnoreCase(
                "Backend Developer")) {

            return new LearningPathResponse(

                    career,

                    List.of(
                            "Java Fundamentals",
                            "Spring Boot Masterclass",
                            "MySQL Database Design"
                    ),

                    List.of(
                            "Student Management System",
                            "E-Commerce Backend"
                    ),

                    List.of(
                            "Oracle Java Certification"
                    )
            );
        }

        if (career.equalsIgnoreCase(
                "Machine Learning Engineer")) {

            return new LearningPathResponse(

                    career,

                    List.of(
                            "Python Programming",
                            "Machine Learning",
                            "Deep Learning"
                    ),

                    List.of(
                            "House Price Prediction",
                            "Resume Analyzer"
                    ),

                    List.of(
                            "TensorFlow Developer Certificate"
                    )
            );
        }

        return new LearningPathResponse(
                career,
                List.of(),
                List.of(),
                List.of()
        );
    }
}