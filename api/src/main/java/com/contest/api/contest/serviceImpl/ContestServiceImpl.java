package com.contest.api.contest.serviceImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.contest.api.contest.domain.Contest;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.dto.ContestDto;
import com.contest.api.contest.repository.ContestRepo;
import com.contest.api.contest.service.ContestService;

@Service
public class ContestServiceImpl implements ContestService {

    private ContestRepo contestRepo;

    public ContestServiceImpl(ContestRepo contestRepo) {
        this.contestRepo = contestRepo;
    }

    @Override
    public ApiRes<Contest> createContest(ContestDto contestDto) {
        try {
            Contest contest = new Contest();
            contest.setContestName(contestDto.getContestName());
            contest.setStartTime(contestDto.getStartTime());
            contest.setEndTime(contestDto.getEndTime());
            contest.setIsPrivate(contestDto.getIsPrivate());
            contest.setIsActive(contestDto.getIsActive());
            contest.setContestDescription(contestDto.getContestDescription());
            contestRepo.save(contest);
            return new ApiRes<>(200, true, "Contest created successfully", contest);
        } catch (Exception e) {
            return new ApiRes<>(500, false, "Error creating contest: " + e.getMessage(), null);
        }
    }

    @Override
    public ApiRes<Contest> getContestById(String contestId) {
        try {
            Optional<Contest> contestOpt = contestRepo.findById(contestId);
            if (!contestOpt.isPresent()) {
                return new ApiRes<>(404, false, "Contest not found", null);
            }
            return new ApiRes<>(200, true, "Contest found",  contestOpt.get());
        } catch (Exception e) {
            return new ApiRes<>(500, false, "Error getting contest: " + e.getMessage(), null);
        }
    }

}
