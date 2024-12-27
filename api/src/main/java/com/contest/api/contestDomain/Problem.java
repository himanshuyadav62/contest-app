package com.contest.api.contestDomain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "problems")
@Data
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "problem_id")
    private String problemId;

    @Column(name = "problem_title", nullable = false)
    private String problemTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "problem_type", nullable = false)
    private ProblemType problemType;

    @Enumerated(EnumType.STRING)
    @Column(name = "problem_difficulty", nullable = false)
    private Difficulty problemDifficulty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id", nullable = false)
    private Contest contest;
    
    @Column(name = "max_points")
    private Integer maxPoints;

}
