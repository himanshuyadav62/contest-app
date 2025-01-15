package com.contest.api.contest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CodeSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String codeSubmissionId;
 
    @Column(name = "code_text_id")
    private String codeTextId;

    @Enumerated(EnumType.STRING)
    private SubAcceptenceStatus subAcceptenceStatus;

    @Column(name = "test_case_passed_count")
    private Integer testCasePassedCount;

    @ManyToOne
    @JoinColumn(name = "coding_problem_id", nullable = false)
    private CodingProblem codingProblem;

    @ManyToOne
    @JoinColumn(name = "contest_participation_id", nullable = false)
    private ContestParticipation contestParticipation;
}
