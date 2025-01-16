package com.contest.api.contest.service;

import com.contest.api.contest.domain.Problem;
import com.contest.api.contest.domain.ProblemType;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.dto.ProblemDto;

public interface ProblemSetterService {

    ApiRes<Problem> createProblem(String contestId, ProblemDto problemDto) ;

    ApiRes<String> createProblemDetails(String problemId, Object problemDetails,
            ProblemType problemType);

    
} 
