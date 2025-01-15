package com.contest.api.contest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contest.api.contest.domain.Contest;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.service.ContestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private ContestService contestService;


    public AdminController(ContestService contestService) {
        this.contestService = contestService;
    }
    
    @GetMapping("/contest/{contestId}")
    public ResponseEntity<ApiRes<Contest>> getContestById(@PathVariable String contestId) {
        ApiRes<Contest> response = this.contestService.getContestById(contestId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    
}
