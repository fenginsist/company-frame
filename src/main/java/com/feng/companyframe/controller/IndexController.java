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

    @ApiOperation(value = "跳转 404 页面")
    @GetMapping("/404")
    public String error404(){
        return "error/404";
    }

    @ApiOperation(value = "跳转 403 页面")
    @GetMapping("/403")
    public String error403(){
        return "error/403";
    }

    @ApiOperation(value = "跳转 500 页面")
    @GetMapping("/500")
    public String error500(){
        return "error/500";
    }

    @ApiOperation(value = "进入登录页面")
    @GetMapping(value = {"/login","/"})
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

    @ApiOperation(value = "跳转角色管理页面")
    @GetMapping(value = "/role")
    public String getRole(){
        return "roles/role";
    }

    @ApiOperation(value = "跳转部门管理页面")
    @GetMapping("/depts")
    public String deptList(){
        return "depts/dept";
    }

    @GetMapping("/users")
    @ApiOperation(value = "跳转用户管理页面")
    public String userList(){
        return "users/user";
    }

    @GetMapping("/logs")
    @ApiOperation(value = "跳转日志管理页面")
    public String logList(){
        return "logs/log";
    }

    @ApiOperation(value = "跳转个人用户信息编辑页面")
    @GetMapping("/user/updateSelfInfo")
    public String userDetail(){
        return "users/user_edit";
    }

    @ApiOperation(value = "跳转个人用户编辑密码页面")
    @GetMapping("/user/editPassword")
    public String userPwd(){
        return "users/edit_password";
    }


    @ApiOperation(value = "跳转到地图界面")
    @GetMapping("/map")
    public String toMap(){
        return "maps/map";
    }


}

