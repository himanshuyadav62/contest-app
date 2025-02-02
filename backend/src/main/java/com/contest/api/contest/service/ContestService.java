package com.contest.api.contest.service;

import java.util.List;

import com.contest.api.contest.domain.Contest;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.dto.ContestDto;

public interface ContestService {

    public ApiRes<Contest> createContest(ContestDto contestDto);

    public ApiRes<Contest> getContestById(String contestId);

    public ApiRes<List<Contest>> getAllContests();    
    
}
