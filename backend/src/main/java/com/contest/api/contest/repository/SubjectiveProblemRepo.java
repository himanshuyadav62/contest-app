package com.contest.api.contest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contest.api.contest.domain.SubjectiveProblem;

public interface SubjectiveProblemRepo extends JpaRepository<SubjectiveProblem, String> {

    @Query("SELECT p FROM SubjectiveProblem p WHERE p.problem.problemId = :problmeId")
    Optional<SubjectiveProblem> findByProblemId(@Param("problmeId") String problemId);
    
}
