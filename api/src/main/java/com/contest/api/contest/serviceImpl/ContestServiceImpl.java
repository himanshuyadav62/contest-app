package com.contest.api.contest.serviceImpl;

import org.springframework.stereotype.Service;

import com.contest.api.contest.domain.Contest;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.repository.ContestRepo;
import com.contest.api.contest.service.ContestService;

@Service
public class ContestServiceImpl implements ContestService {

    private ContestRepo contestRepo;

    public ContestServiceImpl(ContestRepo contestRepo) {
        this.contestRepo = contestRepo;
    }

    @Override
    public ApiRes<Contest> createContest(Contest contest) {
       try {
           return new ApiRes<>(200, true, "Contest created successfully", this.contestRepo.save(contest));
       } catch (Exception e) {
            return new ApiRes<>(500, false, "Contest creation failed", null);
       }
    }
    
}
