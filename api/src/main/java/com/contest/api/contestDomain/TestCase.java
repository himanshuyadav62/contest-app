package com.contest.api.contestDomain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Data
@Table(name = "test_case", uniqueConstraints = {@UniqueConstraint(columnNames = {"testcaseNumber", "problem_id"})})
public class TestCase {

    @Id
    private String testcaseId; 

    private String inputFileId; 

    private String outputFileId;

    private Double timeLimit;

    private Double memoryLimit;

    private Integer testcaseNumber; 

    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

}
