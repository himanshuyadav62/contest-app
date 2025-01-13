package com.contest.api.contest.serviceImpl;

import com.contest.api.contest.domain.File;
import com.contest.api.contest.domain.Problem;
import com.contest.api.contest.dto.ApiRes;
import com.contest.api.contest.dto.ContentDto;
import com.contest.api.contest.dto.ProblemDto;
import com.contest.api.contest.service.FileService;
import com.contest.api.contest.service.ProblemSetterService;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProbSetterServImpl implements ProblemSetterService {

    private FileService fileService;

    public ProbSetterServImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public ApiRes<Problem> createProblem(String contestId, ProblemDto problemDto) {
        List<ContentDto> contents = problemDto.getContentDtos();

        // Sort contents by contentOrder
        Collections.sort(contents, Comparator.comparingInt(ContentDto::getContentOrder));

        // Check if all contentOrder values are distinct
        Set<Integer> orders = new HashSet<>();
        for (ContentDto content : contents) {
            if (!orders.add(content.getContentOrder())) {
                return new ApiRes<>(400, false, "Duplicate contentOrder found", null);
            }
        }

        List<MultipartFile> files = problemDto.getContentDtos().stream().map(ContentDto::getContent).toList();
        List<File> fileInfos = this.fileService.uploadFiles(files);
        Problem problem = new Problem();
        

    }

}
