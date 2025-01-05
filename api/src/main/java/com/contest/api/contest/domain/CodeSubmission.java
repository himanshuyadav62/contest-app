package com.contest.api.contest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CodeSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String codeSubmissionId;
 
    // fields for coding problems result 
    @Column(name = "answer_text", columnDefinition = "TEXT") 
    private String answerText;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @Column(name = "execution_time")
    private Long executionTime;

    @Column(name = "memory_usage")
    private Long memoryUsage;

    @Column(name = "language")
    private String language;

    @Column(name = "test_case_count")
    private Integer testCaseCount;

    @Column(name = "test_case_passed_count")
    private Integer testCasePassedCount;
}
