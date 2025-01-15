package com.contest.api.contest.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(
    name = "mop_submissions",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"contest_participation_id", "mop_id"}
    )
)
public class MOPSubmission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "mop_submission_id")
    private String mopSubmissionId;

    @Enumerated(EnumType.STRING)
    private SubAcceptenceStatus subAcceptenceStatus;

    @OneToMany
    @JoinColumn(name = "mop_submission_id")
    private List<Option> selectedOptions;

    @ManyToOne
    @JoinColumn(name = "contest_participation_id")
    private ContestParticipation contestParticipation;

    @ManyToOne
    @JoinColumn(name = "mop_id")
    private MultipleOptionsProblem multipleOptionsProblem;
}
