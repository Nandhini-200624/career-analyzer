package com.career.analyzer.entity;

import jakarta.persistence.*;
import jakarta.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Entity
@Table(name = "career_profiles")
public class CareerProfile {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String education;

    @Getter
    private String currentSkills;

    @Getter
    private String interestedDomain;

    @Getter
    private String experienceLevel;

    @Getter
    private String careerGoal;
    @JsonIgnore
    @OneToOne(mappedBy = "careerProfile",fetch = FetchType.LAZY)
    private User user;
    // Default Constructor
    public CareerProfile() {
    }

    // Parameterized Constructor
    public CareerProfile(Long id,
                         String education,
                         String currentSkills,
                         String interestedDomain,
                         String experienceLevel,
                         String careerGoal) {

        this.id = id;
        this.education = education;
        this.currentSkills = currentSkills;
        this.interestedDomain = interestedDomain;
        this.experienceLevel = experienceLevel;
        this.careerGoal = careerGoal;
    }

    public List<String> getUser() {
        return (List<String>) user;
    }

}