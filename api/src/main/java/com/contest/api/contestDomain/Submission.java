package com.contest.api.contestDomain;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "submissions")
@Data
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "participation_id", nullable = false)
    private ContestParticipation participation;
    
    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;
    
    @Column(name = "submission_time", nullable = false)
    private Instant submissionTime;
    
    @Column(name = "score")
    private Integer score;

}