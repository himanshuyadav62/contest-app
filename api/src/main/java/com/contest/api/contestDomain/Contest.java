package com.contest.api.contestDomain;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "contests")
@Data
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "contest_id")
    private String contestId;

    @Column(name = "contest_name", nullable = false)
    private String contestName;

    @Column(name = "contest_description", length = 1000)
    private String contestDescription;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;
    
    @Column(name = "end_time", nullable = false)
    private Instant endTime;
    
    @Column(name = "registration_deadline")
    private Instant registrationDeadline;
    
    @OneToMany(mappedBy = "contest")
    private List<ContestParticipation> participations;
}
