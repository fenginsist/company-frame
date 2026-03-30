package com.feng.companyframe.controller;

import com.feng.companyframe.aop.annotation.MyLog;
import com.feng.companyframe.bean.SysUser;
import com.feng.companyframe.constant.Constant;
import com.feng.companyframe.jwt.JwtTokenUtil;
import com.feng.companyframe.services.UserService;
import com.feng.companyframe.utils.DataResult;
import com.feng.companyframe.vo.req.UserLoginUpdateDetailInfoReqVO;
import com.feng.companyframe.vo.req.UserUpdatePWDReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @ClassName: SlefInfoController
 * @Description： 描述
 * @createTime: 2020/3/2 17:51
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/api")
@Api(tags = "组织管理-个人管理", value = "个人信息模块相关接口")
public class SlefInfoController {

    @Resource
    private UserService userService;


    @ApiOperation(value = "查询登录用户信息详情接口")
    @MyLog(title = "组织管理-个人管理", action = "查询登录用户信息详情接口")
    @GetMapping("/user/getLoginUserDetailInfo")
    public DataResult<SysUser> getLoginUserDetailInfo(HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        SysUser loginUserDetailInfo = userService.getLoginUserDetailInfo(userId);
        DataResult<SysUser> result = DataResult.success();
        result.setData(loginUserDetailInfo);
        return result;
    }


    @ApiOperation(value = "更新用户信息接口")
    @MyLog(title = "组织管理-个人管理", action = "个人用户更新用户信息接口")
    @PutMapping("/user/updateSelfInfo")
    public DataResult updateUserInfoById(@RequestBody @Valid UserLoginUpdateDetailInfoReqVO vo,
                                         HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        userService.updateLoginUserDetailInfo(vo, userId);
        return DataResult.success();
    }

    @ApiOperation(value = "修改个人密码接口")
    @MyLog(title = "组织管理-个人管理", action = "修改个人密码接口")
    @PutMapping(value = "/user/updatePassword")
    public DataResult updateSelfPwd(@RequestBody @Valid UserUpdatePWDReqVO vo, HttpServletRequest request) {
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        String refreshToken = request.getHeader(Constant.REFRESH_TOKEN);
        userService.updateLoginUserPassword(vo, accessToken, refreshToken);
        return DataResult.success();
    }

}

