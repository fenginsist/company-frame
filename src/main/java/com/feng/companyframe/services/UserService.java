package com.feng.companyframe.services;

import com.feng.companyframe.bean.SysUser;
import com.feng.companyframe.vo.req.*;
import com.feng.companyframe.vo.resp.UserOwnRoleRespVO;
import com.feng.companyframe.vo.resp.LoginRespVO;
import com.feng.companyframe.vo.resp.PageRespVO;

import java.util.List;

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

    /**
     * 添加新用户
     * @param vo
     */
    void addUser(UserAddReqVO vo);

    /**
     * 根据用户id获取用户拥有角色
     * @param userId
     * @return
     */
    UserOwnRoleRespVO getUserOwnRole(String userId);

    /**
     * 添加用户 权限
     * @param vo
     */
    void setUserOwnRole(UserOwnRoleReqVO vo);

    /**
     * 更新用户信息
     * @param vo
     * @param operationId 操作的人员
     */
    void updateUserInfo(UserUpdateReqVO vo, String operationId);

    /**
     * 删除用户，单个删除和批量删除
     * @param userIdList
     * @param operationId 操作人 id
     */
    void deletedUsers(List<String> userIdList, String operationId);


    /**
     * 自动刷新 token， 使用 refreshToken， 来刷新 AccessToken
     * @param refreshTOken
     * @return
     */
    String refreshToken(String refreshTOken);

    /**
     * 根据部门id集合 获取 用户信息
     * @param deptIds
     * @return
     */
    List<SysUser> getUserInfoByDeptIds(List<String> deptIds);

    /**
     * 根据登录用户id 获取登录用户详细信息
     * @param userId
     * @return
     */
    SysUser getLoginUserDetailInfo(String userId);


    /**
     * 个人用户编辑信息接口
     * @param vo
     */
    void updateLoginUserDetailInfo(UserLoginUpdateDetailInfoReqVO vo, String userId);


    /**
     * 更新个人密码
     *
     * @param vo
     * @param accessToken
     * @param refreshToken
     */
    void updateLoginUserPassword(UserUpdatePWDReqVO vo, String accessToken, String refreshToken);
}
