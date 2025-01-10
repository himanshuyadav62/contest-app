package com.contest.api.contest.dto;

import java.util.List;

import com.contest.api.contest.domain.Content;
import com.contest.api.contest.domain.ProblemType;

import lombok.Data;

@Data
public class ProblemDto {

    private Long problemId; 
    
    private ProblemType problemType; 

    private List<Content> contents;
}
