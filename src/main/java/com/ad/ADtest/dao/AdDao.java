package com.ad.ADtest.dao;

import com.ad.ADtest.dto.Basic;
import com.ad.ADtest.dto.Mobile_Res;
import com.ad.ADtest.dto.ImageInfo;
import com.ad.ADtest.dto.Res_Req;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdDao {


    public boolean sendTeleTwo(Basic param);

    public boolean sendTeleThree(Mobile_Res param);

    public void imageInfo(ImageInfo pc_res);

    //정보 response
    Res_Req bringInfo(Basic param);

    Res_Req bringInfo2(Basic param);

    List<Res_Req> bringUrl(Basic param);

    //중복제거
    Basic deduplicate(String test_tid);
}
