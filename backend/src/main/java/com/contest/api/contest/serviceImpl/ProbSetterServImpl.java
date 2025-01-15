package com.contest.api.contest.serviceImpl;

import com.contest.api.contest.domain.Content;
import com.contest.api.contest.domain.Contest;
import com.contest.api.contest.domain.File;
import com.contest.api.contest.domain.Problem;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.dto.ContentDto;
import com.contest.api.contest.dto.ProblemDto;
import com.contest.api.contest.repository.ContestRepo;
import com.contest.api.contest.repository.ProblemRepo;
import com.contest.api.contest.service.FileService;
import com.contest.api.contest.service.ProblemSetterService;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProbSetterServImpl implements ProblemSetterService {

    private FileService fileService;
    private ProblemRepo problemRepo;
    private ContestRepo contestRepo;

    public ProbSetterServImpl(FileService fileService, ProblemRepo problemRepo, ContestRepo contestRepo) {
        this.fileService = fileService;
        this.problemRepo = problemRepo;
        this.contestRepo = contestRepo;
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

        Problem problem = new Problem(null, problemDto.getProblemType(), contents, contest.get());
        Problem savedProblem = problemRepo.save(problem);

        return new ApiRes<>(201, true, "Problem created successfully", savedProblem);
    }

    private boolean isDistinctContentOrder(List<ContentDto> contentDtos) {
        Set<Integer> orders = new HashSet<>();
        return contentDtos.stream().allMatch(content -> orders.add(content.getContentOrder()));
    }

    private List<Content> mapContentDtosToContents(List<ContentDto> contentDtos) throws IOException  {
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
                        contentDto.getContentOrder()
                );
                contents.add(content);
            }
        return contents;
    }
    

}
