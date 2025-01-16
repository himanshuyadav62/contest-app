package com.contest.api.contest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contest.api.contest.domain.MultipleOptionsProblem;

public interface MultipleOptionProbRepo extends JpaRepository<MultipleOptionsProblem, String> {

    @Query("SELECT p FROM MultipleOptionsProblem p WHERE p.problem.problemId = :problemId")
    Optional<MultipleOptionsProblem> findByProblemId(@Param("problemId") String problemId);
    
}
