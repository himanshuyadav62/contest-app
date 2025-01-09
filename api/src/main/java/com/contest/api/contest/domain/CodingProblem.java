package com.contest.api.contest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity
@Data
public class CodingProblem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "coding_problem_id")
    private String codingProblemId;

    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

}
