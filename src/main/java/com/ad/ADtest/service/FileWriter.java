package com.ad.ADtest.service;

import com.ad.ADtest.config.PhotoAppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Slf4j
@Service
public class FileWriter  {

    private final PhotoAppProperties photoAppProperties;




    public long writeFile(MultipartFile multipartFile, String filePath){
        try {
            multipartFile.transferTo(new File(filePath));
        }catch (IllegalStateException ile){
            throw new RuntimeException("file write error");
        }catch (IOException ioe){
            throw new RuntimeException("ioe error");

        }
        return multipartFile.getSize();
    }


    public String getFilePath(String fileId, MultipartFile sourceFile){
        return photoAppProperties.getDefaultPath() +"/"+ fileId + "." +getMimeType(sourceFile.getOriginalFilename());
    }

    private static String getMimeType(String filePath){
        return FilenameUtils.getExtension(filePath);
    }

    public static String dateStr(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return now.format(dateTimeFormatter);
    }


}
