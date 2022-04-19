package com.ad.ADtest.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadResult {

    private String fileId;
    private String fileName;
    private Long fileSize;



}
