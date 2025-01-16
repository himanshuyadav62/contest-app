package com.contest.api.contest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.contest.api.contest.domain.Contest;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.dto.ContestDto;
import com.contest.api.contest.service.ContestService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@Validated
public class ContestController {

    private ContestService contestService;

    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @PostMapping("/contest")
    public ResponseEntity<ApiRes<Contest>> createConteResponseEntity(@RequestBody ContestDto contestDto) {
        ApiRes<Contest> response = this.contestService.createContest(contestDto);
         return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/contest/{contestId}")
    public ResponseEntity<ApiRes<Contest>> getMethodName(@PathVariable String contestId) {
        ApiRes<Contest> response = this.contestService.getContestById(contestId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    
    @GetMapping("/contests")
    public ResponseEntity<ApiRes<List<Contest>>> getAllContests() {
        ApiRes<List<Contest>> response = this.contestService.getAllContests();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    
}
