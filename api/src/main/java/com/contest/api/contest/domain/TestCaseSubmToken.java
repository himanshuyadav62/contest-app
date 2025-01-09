package com.contest.api.contest.domain;

import jakarta.persistence.Id;

public class TestCaseSubmToken {
    
    @Id
    private String token;
    
    private String codeSubmissionId; 

    private String testCaseId; 
}
