package com.ad.ADtest.service;

import com.ad.ADtest.dao.AdDao;
import com.ad.ADtest.dto.Basic;
import com.ad.ADtest.dto.Mobile_Res;
import com.ad.ADtest.dto.ImageInfo;
import com.ad.ADtest.dto.Res_Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdService {

    @Autowired
    public AdDao dao;

    public boolean sendTeleTwo(Basic param) {
        boolean success =  dao.sendTeleTwo(param);
        return success;
    }

    public boolean sendTeleThree(Mobile_Res param) {
        boolean success =  dao.sendTeleThree(param);
        return success;
    }

    public void imageInfo(ImageInfo pc_res) {
        dao.imageInfo(pc_res);
    }

    public Res_Req bringInfo(Basic param) {
        Res_Req info = dao.bringInfo(param);
        Res_Req info2 = dao.bringInfo2(param);
        List<Res_Req> url = dao.bringUrl(param);

        System.out.println(info);
        System.out.println(info2);
        System.out.println(url);
        List<String> list = new ArrayList<>();
        url.get(0).getImage_path();
        for(int i =0; i< url.size();i++){
            list.add(url.get(i).getImage_path());
        }

        Res_Req res = new Res_Req();
        res.setOs_type(info.getOs_type());
        res.setOs_version(info.getOs_version());
        res.setDevice_name(info.getDevice_name());
        res.setAdid(info.getAdid());
        res.setTc_no(info.getTc_no());
        res.setStart_time(info.getStart_time());
        res.setApp_version(info.getApp_version());
        res.setServer_target(info.getServer_target());
        res.setRequest_data(info2.getRequest_data());
        res.setResponse_data(info2.getResponse_data());
        res.setLog(info2.getLog());
        res.setImages(list);
        res.setExt_1(info2.getExt_1());
        return res ;
    }

    public Basic deduplicate(String test_tid) { return dao.deduplicate(test_tid); }
}
