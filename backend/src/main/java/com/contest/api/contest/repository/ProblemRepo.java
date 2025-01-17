package com.contest.api.contest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contest.api.contest.domain.Problem;
import com.contest.api.contest.dto.ProblemSummary;

public interface ProblemRepo extends JpaRepository<Problem, String> {

    @Query("SELECT new com.contest.api.contest.dto.ProblemSummary(p.problemId, p.problemTitle, p.problemType) " +
            "FROM Problem p WHERE p.contest.contestId = :contestId")
    List<ProblemSummary> findByContestContestId(@Param("contestId") String contestId);

}
