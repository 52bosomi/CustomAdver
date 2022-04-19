package com.ad.ADtest.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class ImageFile {
    private String fileId;
    private Long fileSize;
    private String fileName;
    private String fileType;
    private String filePath;


}
