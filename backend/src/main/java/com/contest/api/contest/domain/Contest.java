package com.contest.api.contest.domain;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Data
@Table(name = "contests")
@EntityListeners(AuditingEntityListener.class)
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "contest_id")
    private String contestId;

    @Column(name = "contest_name", nullable = false)
    @NotNull
    @UpdatableField
    private String contestName;

    @Column(name = "contest_description", length = 1000)
    @UpdatableField
    private String contestDescription;

    @Column(name = "start_time", nullable = false)
    @NotNull
    @UpdatableField
    private Instant startTime;
    
    @Column(name = "end_time", nullable = false)
    @NotNull
    @UpdatableField
    private Instant endTime;

    @Column(name = "is_private", nullable = false)
    @NotNull
    @UpdatableField
    private Boolean isPrivate; 
    
    @Column(name = "registration_deadline")
    @UpdatableField
    private Instant registrationDeadline;

    @UpdatableField
    private Boolean isActive = true;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;

}
