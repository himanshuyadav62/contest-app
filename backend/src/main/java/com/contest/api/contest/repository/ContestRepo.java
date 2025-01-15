package com.contest.api.contest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contest.api.contest.domain.Contest;

public interface ContestRepo extends JpaRepository<Contest, String> {
    
}
