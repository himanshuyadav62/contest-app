package com.contest.api.contest.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.contest.api.contest.domain.File;

public interface FileRepo extends JpaRepository<File, String> {

        
}
