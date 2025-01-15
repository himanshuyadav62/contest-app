package com.contest.api.contest.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TestCaseSubmToken {
    
    @Id
    private String token;
    
    private String codeSubmissionId; 

    private String testCaseId; 
}
