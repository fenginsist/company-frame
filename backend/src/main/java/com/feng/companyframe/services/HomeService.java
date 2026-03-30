package com.feng.companyframe.services;

import com.feng.companyframe.vo.resp.HomeRespVO;

public interface HomeService {

    /**
     * 根据 用户id 获取 页面信息
     * @param userId
     * @return
     */
    HomeRespVO getHome(String userId);
}
