package com.feng.companyframe.controller;

import com.feng.companyframe.aop.annotation.MyLog;
import com.feng.companyframe.services.UserService;
import com.feng.companyframe.utils.DataResult;
import com.feng.companyframe.vo.req.UserOwnRoleReqVO;
import com.feng.companyframe.vo.resp.UserOwnRoleRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName: UserRoleController
 * @Description： 描述
 * @createTime: 2020/2/19 23:06
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Api(tags = "赋予角色操作-用户角色")
@RestController
@RequestMapping("/api")
public class UserRoleController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "赋予角色-根据用户id获取用户 所拥有的角色接口")
    @MyLog(title = "赋予角色操作-用户角色", action = "赋予角色-根据用户id获取用户 所拥有的角色接口")
    @RequiresPermissions("sys:user:role:update")
    @GetMapping("/user/roles/{userId}")
    public DataResult<UserOwnRoleRespVO> getUserOwnRole(@PathVariable("userId") String userId) {
        DataResult<UserOwnRoleRespVO> result = DataResult.success();
        UserOwnRoleRespVO userOwnRole = userService.getUserOwnRole(userId);
        result.setData(userOwnRole);
        return result;
    }

    @ApiOperation(value = "保存用户拥有的角色信息接口，先删除原有的，在添加新的：用户角色信息")
    @MyLog(title = "赋予角色操作-用户角色",action = "保存用户拥有的角色信息接口，先删除原有的，在添加新的：用户角色信息")
    @RequiresPermissions("sys:user:role:update")
    @PutMapping("/user/roles")
    public DataResult saveUserOwnRole(@RequestBody @Valid UserOwnRoleReqVO vo) {
        userService.setUserOwnRole(vo);
        DataResult result = DataResult.success();
        return result;
    }


}

