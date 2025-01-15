package com.contest.api.contest.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContentDto {

    private Long contentId;

    @NotNull
    private MultipartFile content;

    private String fileType; 

    @NotNull
    Integer contentOrder;
}
