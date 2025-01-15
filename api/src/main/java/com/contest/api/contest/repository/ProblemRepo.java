package com.contest.api.contest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contest.api.contest.domain.Problem;

public interface ProblemRepo extends JpaRepository<Problem, String> {
    
}
