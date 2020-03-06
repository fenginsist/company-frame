package com.feng.companyframe.controller;

import com.feng.companyframe.aop.annotation.MyLog;
import com.feng.companyframe.bean.SysPermission;
import com.feng.companyframe.services.PermissionService;
import com.feng.companyframe.utils.DataResult;
import com.feng.companyframe.vo.req.PermissionAddReqVO;
import com.feng.companyframe.vo.req.PermissionUpdateReqVO;
import com.feng.companyframe.vo.resp.PermissionRespNodeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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

    @ApiOperation(value = "获取所有的菜单权限数据列表")
    @MyLog(title = "组织管理-菜单权限管理", action = "获取所有的菜单权限数据列表")
    @RequiresPermissions("sys:permission:list")
    @GetMapping(value = "/permissions")
    public DataResult<List<SysPermission>> getAllPermission() {
        DataResult result = DataResult.success();
        result.setData(permissionService.getAllPermissions());
        return result;
    }

    @ApiOperation(value = "菜单权限树接口-只递归查询到菜单")
    @MyLog(title = "组织管理-菜单权限管理", action = "菜单权限树接口-只递归查询到菜单")
    @RequiresPermissions(value = {"sys:permission:add", "sys:permission:update"}, logical = Logical.OR)
    @GetMapping(value = "/permission/tree")
    public DataResult<List<PermissionRespNodeVO>> getAllPermissionTreeExchangeBtn() {
        DataResult result = DataResult.success();
        result.setData(permissionService.getAllMenuByTree(true));
        return result;
    }

    @ApiOperation(value = "菜单权限树接口-只递归查询到菜单")
    @MyLog(title = "组织管理-菜单权限管理", action = "菜单权限树接口-只递归查询到菜单")
    @RequiresPermissions("sys:permission:add")
    @PostMapping(value = "/addPermission")
    public DataResult<SysPermission> addPermission(@RequestBody @Valid PermissionAddReqVO permissionAddReqVO) {
        DataResult result = DataResult.success();
        SysPermission sysPermission = permissionService.addPermission(permissionAddReqVO);
        result.setData(sysPermission);
        return result;
    }

    @ApiOperation(value = "菜单权限树接口-查询所有：目录、菜单、按钮--给角色添加权限时用到")
    @MyLog(title = "组织管理-菜单权限管理", action = "菜单权限树接口-查询所有：目录、菜单、按钮--给角色添加权限时用到")
    @RequiresPermissions(value = {"sys:role:update", "sys:role:add"}, logical = Logical.OR)
    @GetMapping(value = "/permission/tree/all")
    public DataResult<List<PermissionRespNodeVO>> getAllPermissionTree() {
        DataResult result = DataResult.success();
        result.setData(permissionService.getAllTree());
        return result;
    }

    /*
     * 设计到三个表的操作 permission、user_role、role_permission
     * */
    @ApiOperation(value = "编辑菜单权限接口")
    @MyLog(title = "组织管理-菜单权限管理", action = "编辑菜单权限接口")
    @RequiresPermissions("sys:permission:update")
    @PutMapping(value = "/updatePermission")
    public DataResult updatePermission(@RequestBody @Valid PermissionUpdateReqVO vo) {
        permissionService.updatePermission(vo);
        return DataResult.success();
    }

    /**
     * @param permissionId
     * @return
     */
    @ApiOperation(value = "删除菜单权限")
    @MyLog(title = "组织管理-菜单权限管理", action = "删除菜单权限")
    @RequiresPermissions("sys:permission:delete")
    @DeleteMapping(value = "/deletePermission/{permissionId}")
    public DataResult deletedPermission(@PathVariable("permissionId") String permissionId) {
        permissionService.deletedPermission(permissionId);
        return DataResult.success();
    }

}

