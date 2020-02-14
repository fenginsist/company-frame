package com.feng.companyframe.controller;

import com.feng.companyframe.bean.SysPermission;
import com.feng.companyframe.services.PermissionService;
import com.feng.companyframe.utils.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PermissionController
 * @Description： 描述
 * @createTime: 2020/2/11 17:41
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Api(tags = "组织管理-菜单权限管理", value = "菜单权限管理相关接口")
@RestController
@RequestMapping("/api")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @ApiOperation(value = "获取所有的菜单权限数据")
    @GetMapping(value = "/permissions")
    public DataResult<List<SysPermission>> getAllPermission(){
        DataResult result = DataResult.success();
        result.setData(permissionService.getAllPermissions());
        return result;
    }
}

