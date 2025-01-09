package com.contest.api.contest.domain;

import java.util.List;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Problem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String problemId;

    @Enumerated(EnumType.STRING)
    private ProblemType problemType;

    @OneToMany
    private List<Content> contents;
}
