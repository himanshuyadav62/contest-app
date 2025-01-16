package com.contest.api.contest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "answer_options")
@Data
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String optionId;
    
    @Column(name = "option_text", nullable = false)
    private String optionText;
    
    @Column(name = "is_correct", nullable = false)
    private boolean isCorrect;
}