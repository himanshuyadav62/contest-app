package com.contest.api.contest.serviceImpl;

import com.contest.api.contest.domain.CodingProblem;
import com.contest.api.contest.domain.Content;
import com.contest.api.contest.domain.Contest;
import com.contest.api.contest.domain.File;
import com.contest.api.contest.domain.MultipleOptionsProblem;
import com.contest.api.contest.domain.Option;
import com.contest.api.contest.domain.Problem;
import com.contest.api.contest.domain.ProblemType;
import com.contest.api.contest.domain.SubjectiveProblem;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.dto.ContentDto;
import com.contest.api.contest.dto.ProblemDto;
import com.contest.api.contest.dto.ProblemSummary;
import com.contest.api.contest.repository.CodingProblemRepo;
import com.contest.api.contest.repository.ContestRepo;
import com.contest.api.contest.repository.MultipleOptionProbRepo;
import com.contest.api.contest.repository.ProblemRepo;
import com.contest.api.contest.repository.SubjectiveProblemRepo;
import com.contest.api.contest.service.FileService;
import com.contest.api.contest.service.ProblemSetterService;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProbSetterServImpl implements ProblemSetterService {

    private FileService fileService;
    private ProblemRepo problemRepo;
    private ContestRepo contestRepo;
    private ProblemSpecificService problemSpecificService;

    public ProbSetterServImpl(FileService fileService, ProblemRepo problemRepo, ContestRepo contestRepo,
            ProblemSpecificService problemSpecificService) {
        this.fileService = fileService;
        this.problemRepo = problemRepo;
        this.contestRepo = contestRepo;
        this.problemSpecificService = problemSpecificService;
    }

    @Override
    public ApiRes<Problem> createProblem(String contestId, ProblemDto problemDto) {

        Optional<Contest> contest = contestRepo.findById(contestId);

        if (contest.isEmpty()) {
            return new ApiRes<>(404, false, "Contest not found", null);
        }

        Instant contestStartTime = contest.get().getStartTime();

        if (contestStartTime.isBefore(Instant.now())) {
            return new ApiRes<>(400, false, "Contest has already started", null);
        }

        List<ContentDto> contentDtos = problemDto.getContentDtos();

        // Sort and validate contentDtos by contentOrder
        contentDtos.sort(Comparator.comparingInt(ContentDto::getContentOrder));

        if (!isDistinctContentOrder(contentDtos)) {
            return new ApiRes<>(400, false, "Duplicate contentOrder found", null);
        }

        List<Content> contents = null;
        try {
            contents = mapContentDtosToContents(contentDtos);
        } catch (Exception e) {
            return new ApiRes<>(500, false, "Failed to upload files", null);
        }

        Problem problem = new Problem(null, problemDto.getProblemType(),problemDto.getProblemTitle(), contents, contest.get());
        Problem savedProblem = problemRepo.save(problem);

        return new ApiRes<>(201, true, "Problem created successfully", savedProblem);
    }

    private boolean isDistinctContentOrder(List<ContentDto> contentDtos) {
        Set<Integer> orders = new HashSet<>();
        return contentDtos.stream().allMatch(content -> orders.add(content.getContentOrder()));
    }

    private List<Content> mapContentDtosToContents(List<ContentDto> contentDtos) throws IOException {
        List<Content> contents = new ArrayList<>();

        List<MultipartFile> files = contentDtos.stream()
                .map(ContentDto::getContent)
                .toList();

        List<File> fileInfos = fileService.uploadFiles(files);

        for (int i = 0; i < contentDtos.size(); i++) {
            ContentDto contentDto = contentDtos.get(i);
            File fileInfo = fileInfos.get(i);
            Content content = new Content(
                    null,
                    fileInfo.getFileType(),
                    fileInfo.getFileId(),
                    contentDto.getContentOrder());
            contents.add(content);
        }
        return contents;
    }

    @Override
    public ApiRes<String> createProblemDetails(String problemId, Object problemDetails,
            ProblemType problemType) {

        Optional<Problem> problem = problemRepo.findById(problemId);
        if (problem.isEmpty()) {
            return new ApiRes<>(404, false, "Problem not found", null);
        }

        if (problem.get().getProblemType() != problemType) {
            return new ApiRes<>(400, false, "Problem type mismatch", null);
        }

        return switch (problemDetails) {
            case MultipleOptionsProblem multipleOptionsProblem ->
                problemSpecificService.createMultipleOptionsProblemDetails(problemId, multipleOptionsProblem);
            case SubjectiveProblem subjectiveProblem ->
                this.problemSpecificService.createSubjectiveProblemDetails(problemId, subjectiveProblem);
            case CodingProblem codingProblem ->
                this.problemSpecificService.createCodingProblemDetails(problemId, codingProblem);
            default ->
                new ApiRes<>(400, false, "Invalid problem details", null);
        };

    }

    @Override
    public ApiRes<Map<ProblemType, List<ProblemSummary>>> getAllProblemsGroupedByType(String contestId) {

        List<ProblemSummary> problems = this.problemRepo.findByContestContestId(contestId);

        // Group by problemType
        Map<ProblemType, List<ProblemSummary>> groupedProblems = problems.stream()
            .collect(Collectors.groupingBy(ProblemSummary::problemType));

        return new ApiRes<>(200, true, "Problems grouped by type successfully", groupedProblems);
    }

}

@Service
class ProblemSpecificService {

    private MultipleOptionProbRepo multipleOptionProbRepo;
    private SubjectiveProblemRepo subjectiveProblemRepo;
    private CodingProblemRepo codingProblemRepo;

    public ApiRes<String> createMultipleOptionsProblemDetails(String problemId,
            MultipleOptionsProblem multipleOptionsProblem) {

        Optional<MultipleOptionsProblem> existingProblem = multipleOptionProbRepo.findByProblemId(problemId);
        try {
            if (existingProblem.isPresent()) {
                multipleOptionsProblem.setMopId(existingProblem.get().getMopId());
            }

            validateOptions(multipleOptionsProblem, existingProblem.get());

            MultipleOptionsProblem savedProblem = this.multipleOptionProbRepo.save(multipleOptionsProblem);
            return new ApiRes<>(201, true, "Problem saved successfully", savedProblem.getMopId());
        } catch (IllegalArgumentException e) {
            return new ApiRes<>(400, false, e.getMessage(), null);
        } catch (Exception e) {
            return new ApiRes<>(500, false, "Failed to save problem", null);
        }

    }

    public ApiRes<String> createCodingProblemDetails(String problemId, CodingProblem codingProblem) {
        try {
            Optional<CodingProblem> existingProblem = codingProblemRepo.findByProblemId(problemId);
            if (existingProblem.isPresent()) {
            codingProblem.setCodingProblemId(existingProblem.get().getCodingProblemId());
            }
            this.codingProblemRepo.save(codingProblem);
            return new ApiRes<>(201, true, "Problem saved successfully", null);
        } catch (Exception e) {
            return new ApiRes<>(500, false, "Failed to save problem", null);
        }
    }

    public ApiRes<String> createSubjectiveProblemDetails(String problemId, SubjectiveProblem subjectiveProblem) {
        try {
            Optional<SubjectiveProblem> existingProblem = subjectiveProblemRepo.findByProblemId(problemId);
            if (existingProblem.isPresent()) {
                subjectiveProblem.setSubProbId(existingProblem.get().getSubProbId());
            }
            this.subjectiveProblemRepo.save(subjectiveProblem);
            return new ApiRes<>(201, true, "Problem saved successfully", null);
        } catch (Exception e) {
            return new ApiRes<>(500, false, "Failed to save problem", null);
        } 
    }

    private void validateOptions(MultipleOptionsProblem multipleOptionsProblem,
            MultipleOptionsProblem existingProblem) {
        Set<String> existingOptionIds = existingProblem != null ? existingProblem.getOptions().stream()
                .map(Option::getOptionId)
                .collect(Collectors.toSet()) : Collections.emptySet();

        for (Option option : multipleOptionsProblem.getOptions()) {
            if (option.getOptionId() != null && !existingOptionIds.contains(option.getOptionId())) {
                throw new IllegalArgumentException("Option ID mismatch found for option: " + option.getOptionId());
            }
        }
    }

}
