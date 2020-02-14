package com.feng.companyframe.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: IndexController
 * @Description： 描述
 * @createTime: 2020/2/9 23:23
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Api(tags = "视图", value = "负责返回视图")
@RequestMapping("/index")
@Controller
public class IndexController {

    @ApiOperation(value = "跳转404页面")
    @GetMapping("/404")
    public String error404(){
        return "error/404";
    }

    @ApiOperation(value = "进入登录页面")
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @ApiOperation(value = "进入home页面")
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @ApiOperation(value = "进入 main 页面")
    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @ApiOperation(value = "跳转菜单权限管理页面")
    @GetMapping(value = "/menus")
    public String getMenu(){
        return "menus/menu";
    }
}

