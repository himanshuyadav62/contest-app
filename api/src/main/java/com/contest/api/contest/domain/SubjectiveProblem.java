package com.contest.api.contest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class SubjectiveProblem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String subjectiveProblemId;

    @Column(name = "answer_id")
    private String answerId; 

    @Column(name = "answer_text_id")
    private String answerTextId; 

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

}
