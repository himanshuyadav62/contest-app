package com.contest.api.contest.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ContestDto {

    private String contestId;

    private String contestName;

   
    private String contestDescription;

   
    @NotNull
    private Instant startTime;
    
   
    @NotNull
    private Instant endTime;

    
    @NotNull
    private Boolean isPrivate; 
    
    private Instant registrationDeadline;

    private Boolean isActive;

}

