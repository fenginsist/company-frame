package com.feng.companyframe.controller;

import com.feng.companyframe.aop.annotation.MyLog;
import com.feng.companyframe.bean.SysUser;
import com.feng.companyframe.constant.Constant;
import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.jwt.JwtTokenUtil;
import com.feng.companyframe.services.UserService;
import com.feng.companyframe.utils.DataResult;
import com.feng.companyframe.vo.req.*;
import com.feng.companyframe.vo.resp.LoginRespVO;
import com.feng.companyframe.vo.resp.PageRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: UserController
 * @Description： 描述
 * @createTime: 2020/2/4 17:04
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/api")
@Api(tags = "组织管理-用户管理", value = "用户模块相关接口")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户登录接口")
    @PostMapping(value = "/user/login")
    public DataResult<LoginRespVO> login(@RequestBody LoginReqVO loginReqVO) {
        LoginRespVO login = userService.login(loginReqVO);
        return new DataResult<LoginRespVO>(login);
    }


    @ApiOperation(value = "退出接口")
    @GetMapping("/user/logout")
    public DataResult logout(HttpServletRequest request) {
        // 不管是否出现异常 都要退出
        try {
            String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
            String refreshToken = request.getHeader(Constant.REFRESH_TOKEN);
            userService.logout(accessToken, refreshToken);
        } catch (Exception e) {
            log.error("logout error{}", e.getLocalizedMessage());
        }
        return DataResult.success();
    }

    @ApiOperation(value = "引导客户端去登录")
    @GetMapping("/user/unLogout")
    public DataResult unLogout(HttpServletRequest request) {
        DataResult result = DataResult.getResult(BaseResponseCode.TOKEN_ERROR);
        return result;
    }

    @ApiOperation(value = "分页获取用户列表接口")
    @MyLog(title = "组织管理-用户管理",action = "分页获取用户列表接口")
    @RequiresPermissions("sys:user:list")
    @PostMapping("/users")
    public DataResult<PageRespVO<SysUser>> pageInfo(@RequestBody UserPageReqVO vo) {
        DataResult<PageRespVO<SysUser>> result = DataResult.success();
        result.setData(userService.pageInfo(vo));
        return result;
    }

    @ApiOperation(value = "新增用户接口")
    @MyLog(title = "组织管理-用户管理",action = "新增用户接口")
    @RequiresPermissions("sys:user:add")
    @PostMapping("/addUser")
    public DataResult addUser(@RequestBody @Valid UserAddReqVO vo){
        userService.addUser(vo);
        return DataResult.success();
    }

    @ApiOperation(value = "列表更新用户信息接口")
    @MyLog(title = "组织管理-用户管理",action = "列表更新用户信息接口")
    @RequiresPermissions("sys:user:update")
    @PutMapping("/updateUser")
    public DataResult updateUserInfo(@RequestBody @Valid UserUpdateReqVO vo, HttpServletRequest
            request){
        String operationId= JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        userService.updateUserInfo(vo,operationId);
        return DataResult.success();
    }


    @ApiOperation(value = "批量/删除用户接口")
    @MyLog(title = "组织管理-用户管理",action = "批量/删除用户接口")
    @RequiresPermissions("sys:user:delete")
    @DeleteMapping(value = "/deletedUser")
    public DataResult deletedUser(@RequestBody @ApiParam(value = "用户 id 集合")List<String> userIdList, HttpServletRequest request){
        String operationId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        userService.deletedUsers(userIdList, operationId);
        return DataResult.success();
    }

    @ApiOperation(value = "用户刷新token接口")
    @MyLog(title = "组织管理-用户管理",action = "用户刷新token接口")
    @GetMapping("/user/token")
    public DataResult<String> refreshToken(HttpServletRequest request){
        String refreshToken=request.getHeader(Constant.REFRESH_TOKEN);
        DataResult<String> result = DataResult.success();
        result.setData(userService.refreshToken(refreshToken));
        return result;
    }

}

