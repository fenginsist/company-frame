package com.feng.companyframe.services;

import com.feng.companyframe.bean.SysUser;
import com.feng.companyframe.vo.req.LoginReqVO;
import com.feng.companyframe.vo.req.UserPageReqVO;
import com.feng.companyframe.vo.resp.LoginRespVO;
import com.feng.companyframe.vo.resp.PageRespVO;

public interface UserService {


    /**
     * 登录
     * @param loginReqVO
     * @return
     */
    LoginRespVO login(LoginReqVO loginReqVO);

    /**
     * 退出
     * @param accessToken
     * @param refreshToken
     */
    void logout(String accessToken, String refreshToken);

    /**
     *  分页查询用户信息
     * @param userPageReqVO
     * @return com.feng.companyframe.vo.resp.PageRespVO<com.feng.companyframe.bean.SysUser>
     */
    PageRespVO<SysUser> pageInfo(UserPageReqVO userPageReqVO);


}
