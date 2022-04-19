package com.ad.ADtest.controller;

import com.ad.ADtest.dto.*;
import com.ad.ADtest.service.AdService;
import com.ad.ADtest.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RequiredArgsConstructor
@Controller
public class AdController {

    /**
     * @Created by summer
     * @Date: 2022/02/15
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FileUploadService uploadService;
    private final AdService service;



    @RequestMapping("/")
    public @ResponseBody String accept(){
        System.out.println("서버접속");
        return "서버접속";
    }

    @RequestMapping(value = "/basic-info",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> sendTeleTwo(@RequestBody Basic param){
        String paramTest_tid = param.getTest_tid();
        Basic test_tid = service.deduplicate(paramTest_tid);
        if(test_tid == null){
            boolean success = service.sendTeleTwo(param);
            if(success){
                return new ResponseEntity<>("success", HttpStatus.OK);
            }
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
     return new ResponseEntity<>("duplicated data", HttpStatus.OK);
    }

    @RequestMapping("/apptest-result")
    public @ResponseBody ResponseEntity<Object> sendTeleThree(@RequestBody Mobile_Res param){
        boolean success = service.sendTeleThree(param);

        if(success){
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/pctest-result" ,method = RequestMethod.POST)
    public ResponseEntity<Object> transfer(@RequestPart("imageFile")MultipartFile[] multipartFile,
                                   @RequestParam("test_tid")String test_tid,
                                   @RequestParam("user_id")String user_id,
                                   @RequestParam("ext_1")String ext_1){

        System.out.println("테스트"+test_tid+user_id+ext_1);
        // TODO: 아무것도 안 넣었을때도 파일이 생성된다... 예외 처리 v
        // TODO: 파일이 한꺼번에 여러개 들어올 수 있다... file array, 반복문으로 돌려서 넣으면 될까?
        // TODO: controller 말고 service 단으로 옮기기
        // TODO: 파일 저장 위치 서버에 설정
        String fileExist ="";
        ImageInfo pc_res = new ImageInfo();


             //n개를 다 비교해서 꺼내서 값이 있으면 다시 multi[]에 넣고 그걸 foreach문으로 돌린다

              for(MultipartFile file : multipartFile ){
                  if(!file.getOriginalFilename().isEmpty()){

                      System.out.println("들어온 파일의 이름:"+file.getOriginalFilename());
                      logger.info("파일업로드");
                      ImageFile imageFile = uploadService.upload(file);
                      System.out.println("결과값"+imageFile.getFileId());
                      UploadResult result = UploadResult.builder()
                              .fileName(imageFile.getFileName())
                              .fileId(imageFile.getFileId())
                              .fileSize(imageFile.getFileSize())
                              .build();
                      System.out.println("결과"+result.getFileId());
                      fileExist = "TRUE";

                      pc_res.setOriginal_name(file.getOriginalFilename());//원래이름
                      pc_res.setImage_path(imageFile.getFilePath());//파일저장경로+저장명
                      //현재시각
                      LocalDateTime now = LocalDateTime.now();
                      String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                      pc_res.setInsert_time(formatedNow);
                      pc_res.setExt_1(ext_1);
                      pc_res.setTest_tid(test_tid);
                      pc_res.setUser_id(user_id);
                      //원본명, url, 현재시각
                      service.imageInfo(pc_res);
                  }
             }
            if(fileExist.equals("")){
                return new ResponseEntity<>("No File",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Success",HttpStatus.OK);
    }
    


    //4번 전문 - 외부에서 파일을 받아서 DB에 정보 저장, 폴더에 사진저장
    //파일도 포스트맨으로 테스트 가능. 컨트롤러에서 어떻게 받아주는지 알아봐야함

    //다 넘겨주는 마지막 전문 만들기
    //5번 전문 - DB 에 담긴 값들을 그대로 돌려주고 이미지는 URL로 반환한다.
    @RequestMapping(value = "/all-info",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> bringInfo(@RequestBody Basic param){
        System.out.println(param);
        Res_Req info =  service.bringInfo(param);

        return ResponseEntity.ok(info);
    }


}
