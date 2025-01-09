package com.contest.api.contest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class SubjectiveProblem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String subjectiveProblemId;

    @Column(name = "problem_id")
    private String answerId; 

    @Column(name = "answer_text_id")
    private String answerTextId; 

}
