package com.ad.ADtest.config;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Configuration
public class MultipartConfig {
    @Value("${FileUploadApp.file.defaultPath}")
    public String defaultPath = "";


    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setResolveLazily(true);//파일 또는 매개 변수 액세스 시점에서 다중 파트 요청을 느리게 해결할지 여부를 결정
        multipartResolver.setMaxUploadSize(1024 * 1024 * 10);//파일 사이즈 결정
        multipartResolver.setDefaultEncoding(StandardCharsets.UTF_8.displayName());//UTF-8 로 파일 인코딩을 한다

        try {
            log.info("path:" + defaultPath);
            multipartResolver.setUploadTempDir(new FileSystemResource(defaultPath));
        }catch (Exception e){
            log.error("init error",e);
        }
        return multipartResolver;
    }


}
