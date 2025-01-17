package com.contest.api.contest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.contest.api.contest.domain.Problem;
import com.contest.api.contest.domain.ProblemType;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.dto.ProblemDto;
import com.contest.api.contest.dto.ProblemSummary;
import com.contest.api.contest.service.ProblemSetterService;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/problemsetter")
public class ProblemSetterController {

    private ProblemSetterService problemSetterService;

    public ProblemSetterController(ProblemSetterService problemSetterService) {
        this.problemSetterService = problemSetterService;
    }

    @PostMapping("/problem")
    public ResponseEntity<ApiRes<Problem>> createProblem(@RequestParam String contestId,
            @ModelAttribute ProblemDto problemDto) {
        ApiRes<Problem> apiRes = this.problemSetterService.createProblem(contestId, problemDto);
        return ResponseEntity.status(apiRes.getStatusCode()).body(apiRes);
    }

    @PostMapping("/createProblemDetails/{problemId}")
    public ResponseEntity<ApiRes<String>> createProblemDetails(
            @PathVariable String problemId,
            @RequestParam ProblemType problemType,
            @RequestBody Object problemDetails) {
        ApiRes<String> apiRes = this.problemSetterService.createProblemDetails(problemId, problemDetails,
                problemType);
        return ResponseEntity.status(apiRes.getStatusCode()).body(apiRes);
    }

    @GetMapping("/problem/summary")
    public ResponseEntity<ApiRes<Map<ProblemType,List<ProblemSummary>>>> getAllProblems(@RequestParam String contestId) {
        ApiRes<Map<ProblemType,List<ProblemSummary>>> apiRes = this.problemSetterService.getAllProblemsGroupedByType(contestId);
        return ResponseEntity.status(apiRes.getStatusCode()).body(apiRes);
    }

}
