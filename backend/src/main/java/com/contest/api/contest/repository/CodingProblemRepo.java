package com.contest.api.contest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.contest.api.contest.domain.CodingProblem;

public interface CodingProblemRepo extends JpaRepository<CodingProblem, String> {

    @Query("SELECT p FROM CodingProblem p WHERE p.problem.problemId = ?1")
    Optional<CodingProblem> findByProblemId(String problemId);
    
}
