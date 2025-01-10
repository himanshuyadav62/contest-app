package com.contest.api.contest.service;

import com.contest.api.contest.domain.Contest;
import com.contest.api.contest.dto.ApiRes;

public interface ContestService {

    public ApiRes<Contest> createContest(Contest contest);    
    
}
