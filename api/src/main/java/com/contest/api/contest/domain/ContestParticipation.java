package com.contest.api.contest.domain;

import java.time.Instant;
import java.util.List;

import com.contest.api.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "contest_participations")
@Data
public class ContestParticipation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "contest_id", nullable = false)
    private Contest contest;
    
    @Column(name = "registration_time")
    private Instant registrationTime;
    
    @Column(name = "total_score")
    private Integer totalScore;
    
    @OneToMany(mappedBy = "participation", cascade = CascadeType.ALL)
    private List<Submission> submissions;
}