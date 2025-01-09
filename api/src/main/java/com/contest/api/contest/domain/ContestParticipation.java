package com.contest.api.contest.domain;

import java.time.Instant;
import com.contest.api.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.UniqueConstraint;

@Entity
@Data
@Table(name = "contest_participations", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"contest_id", "user_id"})
})
public class ContestParticipation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(name = "registration_time")
    private Instant registrationTime;
    
    @Column(name = "total_score")
    private Integer totalScore;

    @ManyToOne
    @JoinColumn(name = "contest_id", nullable = false)
    private Contest contest;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
}