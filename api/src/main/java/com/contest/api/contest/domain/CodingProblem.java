package com.contest.api.contest.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class CodingProblem {
    
    @Id
    private String codingProblemId;

    @OneToMany
    private List<Content> contents;

    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

}
