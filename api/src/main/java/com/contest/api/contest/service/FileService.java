package com.contest.api.contest.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.contest.api.contest.domain.File;

public interface FileService {
    
    List<File> uploadFiles(List<MultipartFile> files) throws IOException;
}
