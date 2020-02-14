package com.feng.companyframe.controller;

import com.feng.companyframe.constant.Constants;
import com.feng.companyframe.jwt.JwtTokenUtil;
import com.feng.companyframe.services.HomeService;
import com.feng.companyframe.utils.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: HomeController
 * @Description： 描述
 * @createTime: 2020/2/11 13:07
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Api(tags = "首页模块", value = "描述首页相关模块")
@RestController
@RequestMapping("/api")
public class HomeController {

    @Resource
    private HomeService homeService;

    @ApiOperation(value = "获取首页信息")
    @GetMapping("/home")
    public DataResult getHome(HttpServletRequest request){
        String accessToken = request.getHeader(Constants.ACCESS_TOKEN);
        String userId = JwtTokenUtil.getUserId(accessToken);
        DataResult result = DataResult.success();
        result.setData(homeService.getHome(userId));
        return  result;
    }



}

