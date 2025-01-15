package com.contest.api.contest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Table;

@Entity
@Data
@Table(
    name = "sub_prob_submission",
    uniqueConstraints = @UniqueConstraint(columnNames = {"sub_prob_id", "contest_participation_id"})
)
public class SubProbSumission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String subProbSubId;

    @Enumerated(EnumType.STRING)
    private SubAcceptenceStatus subAcceptenceStatus;

    @Column(name = "sub_prob_sub_text")
    private String subProbSubText;

    @ManyToOne
    @JoinColumn(name = "sub_prob_id")
    private SubjectiveProblem subjectiveProblem;

    @ManyToOne
    @JoinColumn(name = "contest_participation_id")
    private ContestParticipation contestParticipation;

}
