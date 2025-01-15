package com.contest.api.contest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contest.api.contest.domain.Problem;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.dto.ProblemDto;
import com.contest.api.contest.service.ProblemSetterService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/problemsetter")
public class ProblemSetterController {
    
    private ProblemSetterService problemSetterService;

    public ProblemSetterController(ProblemSetterService problemSetterService) {
        this.problemSetterService = problemSetterService;
    }

    @PostMapping("/createProblem/{contestId}")
    public ResponseEntity<ApiRes<Problem>> createProblem(@PathVariable String contestId, @ModelAttribute ProblemDto problemDto) {
        ApiRes<Problem> apiRes = this.problemSetterService.createProblem(contestId, problemDto);
        return ResponseEntity.status(apiRes.getStatusCode()).body(apiRes);
    }
    
}
