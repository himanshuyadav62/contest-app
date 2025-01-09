package com.contest.api.contest.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Data
@Table(name = "test_case", uniqueConstraints = {@UniqueConstraint(columnNames = {"testcaseId", "testcase", })})
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String testcaseId; 

    private String inputFileId; 

    private String outputFileId;

    private Double timeLimit;

    private Double memoryLimit;

    private Integer testCaseNumber; 

    @ManyToOne
    @JoinColumn(name = "coding_problem_id", nullable = false)
    private CodingProblem codingProblem;

}
