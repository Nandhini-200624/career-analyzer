package com.career.analyzer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    @Lob
    private String extractedText;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
