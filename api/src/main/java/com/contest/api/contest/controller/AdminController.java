package com.contest.api.contest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contest.api.contest.domain.Contest;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.service.ContestService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private ContestService contestService;


    public AdminController(ContestService contestService) {
        this.contestService = contestService;
    }
    

    @PostMapping("/createContest")
    public ResponseEntity<ApiRes<Contest>> createContest(@RequestBody Contest contest){
        ApiRes<Contest> apiRes = this.contestService.createContest(contest);
        return ResponseEntity.status(apiRes.getStatusCode()).body(apiRes);
    }
}
