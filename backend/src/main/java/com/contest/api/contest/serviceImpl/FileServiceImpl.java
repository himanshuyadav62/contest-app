package com.contest.api.contest.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.contest.api.contest.domain.File;
import com.contest.api.contest.repository.FileRepo;
import com.contest.api.contest.service.FileService;

@Service
public class FileServiceImpl implements FileService {

    private FileRepo fileRepo;

    @Override
    @Transactional
    public List<File> uploadFiles(List<MultipartFile> files) throws IOException {
        List<File> fileInfos = new ArrayList<>();
        for (MultipartFile file : files) {
            File fileInfo = new File(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            fileInfos.add(fileInfo);
        }
        return fileRepo.saveAll(fileInfos);
    }

}
