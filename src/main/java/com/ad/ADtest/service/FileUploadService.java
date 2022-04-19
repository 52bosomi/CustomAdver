package com.ad.ADtest.service;

import com.ad.ADtest.dto.ImageFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileUploadService {

    private final FileWriter fileWriter;

    public ImageFile upload(MultipartFile sourceFile){
        String fileId = UUID.randomUUID().toString();
        String filePath = fileWriter.getFilePath(fileId,sourceFile);
        log.info("filePath:: {}",filePath);
        fileWriter.writeFile(sourceFile,filePath);

        return ImageFile.builder()
                .fileName(sourceFile.getName())
                .filePath(filePath)
                .fileId(fileId)
                .fileSize(sourceFile.getSize())
                .build();
    }

}
