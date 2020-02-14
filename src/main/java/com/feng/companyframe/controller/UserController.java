package com.feng.companyframe.controller;

import com.feng.companyframe.bean.SysUser;
import com.feng.companyframe.constant.Constants;
import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.services.UserService;
import com.feng.companyframe.utils.DataResult;
import com.feng.companyframe.vo.req.LoginReqVO;
import com.feng.companyframe.vo.req.UserPageReqVO;
import com.feng.companyframe.vo.resp.LoginRespVO;
import com.feng.companyframe.vo.resp.PageRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: UserController
 * @Description： 描述
 * @createTime: 2020/2/4 17:04
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/api")
@Api("用户模块相关说明")
public class UserController {

    @Autowired
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
        String accessToken = request.getHeader(Constants.ACCESS_TOKEN);
        String refreshToken = request.getHeader(Constants.REFRESH_TOKEN);
        userService.logout(accessToken, refreshToken);
        return DataResult.success();
    }

    @ApiOperation(value = "引导客户端去登录")
    @GetMapping("/user/unLogout")
    public DataResult unLogout(HttpServletRequest request) {
        DataResult result = DataResult.getResult(BaseResponseCode.TOKEN_ERROR);
        return result;
    }

    @ApiOperation(value = "分页获取用户列表接口")
    @PostMapping("/users")
    @RequiresPermissions("sys:user:list")
    public DataResult<PageRespVO<SysUser>> pageInfo(@RequestBody UserPageReqVO vo) {
        DataResult<PageRespVO<SysUser>> result = DataResult.success();
        result.setData(userService.pageInfo(vo));
        return result;
    }

}

