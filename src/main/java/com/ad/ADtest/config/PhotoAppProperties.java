package com.ad.ADtest.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.RequestContextFilter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Configuration
@Data
public class PhotoAppProperties {
        @Value("${FileUploadApp.file.defaultPath}")
        private String defaultPath;

        @PostConstruct
        private void init() {log.info("path:: {}", this.defaultPath);    }
}
//아니면 원래대로 경로설정해놔도 EC2에 폴더 지정해놔서 거기로 경로 박아두면 되지않을까????
//아니면 EC2에 FTP서버 설치해둔다음에 거기다가 넣으면 되지않을까