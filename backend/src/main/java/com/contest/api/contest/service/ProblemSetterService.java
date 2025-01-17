package com.contest.api.contest.service;

import java.util.List;
import java.util.Map;

import com.contest.api.contest.domain.Problem;
import com.contest.api.contest.domain.ProblemType;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.dto.ProblemDto;
import com.contest.api.contest.dto.ProblemSummary;

public interface ProblemSetterService {

    ApiRes<Problem> createProblem(String contestId, ProblemDto problemDto);

    ApiRes<String> createProblemDetails(String problemId, Object problemDetails,
            ProblemType problemType);

    ApiRes<Map<ProblemType, List<ProblemSummary>>> getAllProblemsGroupedByType(String contestId);

}
