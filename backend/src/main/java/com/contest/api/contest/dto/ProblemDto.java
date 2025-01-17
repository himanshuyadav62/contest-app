package com.contest.api.contest.dto;

import java.util.List;
import com.contest.api.contest.domain.ProblemType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProblemDto {

    private Long problemId; 
    
    @NotNull
    private ProblemType problemType; 

    private String problemTitle; 

    @NotNull
    private List<ContentDto> contentDtos;
}
