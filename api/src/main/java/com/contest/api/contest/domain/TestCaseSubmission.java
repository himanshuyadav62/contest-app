package com.contest.api.contest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;

@Entity
@Table(name = "test_case_submission", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"code_submission_id", "test_case_id"})
})
public class TestCaseSubmission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String testCaseSubmissionId;

    @Column(name = "memory_used")
    private Float memoryUsed; 

    @Column(name = "time_taken")
    private Float timeTaken;

    @Column(name = "test_case_accepted")
    private Boolean testCaseAccepted;

    @Column(name = "final_verdict")
    private String finalVerdict; 

    @Column(name = "std_err")
    private String stdErr;

    @ManyToOne
    @JoinColumn(name = "code_submission_id", foreignKey = @ForeignKey(name = "fk_code_submission"))
    private CodeSubmission codeSubmission;

    @ManyToOne
    @JoinColumn(name = "test_case_id", foreignKey = @ForeignKey(name = "fk_test_case"))
    private TestCase testCase;
}
