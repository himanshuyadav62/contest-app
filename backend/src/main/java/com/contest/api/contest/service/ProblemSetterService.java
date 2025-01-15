package com.contest.api.contest.service;

import com.contest.api.contest.domain.Problem;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.dto.ProblemDto;

public interface ProblemSetterService {

    ApiRes<Problem> createProblem(String contestId, ProblemDto problemDto) ;

    
} 
