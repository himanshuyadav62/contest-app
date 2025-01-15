package com.contest.api.contest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.contest.api.contest.domain.Contest;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.dto.ContestDto;
import com.contest.api.contest.service.ContestService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    
}
