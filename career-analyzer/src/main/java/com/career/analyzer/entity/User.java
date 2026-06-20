package com.career.analyzer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;
    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "career_profile_id")
    private CareerProfile careerProfile;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public CareerProfile getCareerProfile() {
        return careerProfile;

    }
    public void setCareerProfile(
            CareerProfile careerProfile) {

        this.careerProfile = careerProfile;
    }


}