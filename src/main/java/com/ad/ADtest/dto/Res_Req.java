package com.ad.ADtest.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Res_Req {

    private String os_type;
    private String os_version;
    private String device_name;
    private String adid;
    private String tc_no;
    private String start_time;
    private String app_version;
    private String server_target;

    private String request_data;
    private String response_data;
    private String log;
    private String image_path;
    private List<String> images;
    private String ext_1;

}
