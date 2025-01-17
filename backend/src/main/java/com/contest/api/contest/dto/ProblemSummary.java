package com.contest.api.contest.dto;

import com.contest.api.contest.domain.ProblemType;

public record ProblemSummary(String problemId, String problemTitle, ProblemType problemType) {
}
