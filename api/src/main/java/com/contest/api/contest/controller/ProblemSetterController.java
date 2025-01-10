package com.contest.api.contest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/problemsetter")
public class ProblemSetterController {
    

    @PostMapping("/createProblem/{contestId}")
    public String createProblem(@PathVariable String contestId, @RequestBody String problem) {
        return "Problem created successfully";
       
    }
    
}
