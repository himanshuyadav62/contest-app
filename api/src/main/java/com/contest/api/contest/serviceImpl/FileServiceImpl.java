package com.contest.api.contest.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.contest.api.contest.domain.Content;
import com.contest.api.contest.domain.File;
import com.contest.api.contest.repository.FileRepo;
import com.contest.api.contest.service.FileService;

public class FileServiceImpl implements FileService {

    private FileRepo fileRepo;

    @Override
    @Transactional
    public List<File> uploadFiles(List<MultipartFile> files) {
        List<File> fileInfos = new ArrayList<>();
        files.forEach(file -> {
            File fileInfo = new File(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            fileInfos.add(fileInfo);
        });
        return fileRepo.saveAll(fileInfos);
    }
    
}
