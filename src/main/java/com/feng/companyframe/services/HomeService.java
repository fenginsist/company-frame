package com.feng.companyframe.services;

import com.feng.companyframe.vo.resp.HomeRespVO;

public interface HomeService {

    HomeRespVO getHome(String userId);
}
