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

    /**
     * layui的前端中：在权限菜单管理页面展示表格数据
     * 获取所有目录、菜单（页面）、按钮（CRUD）数据，都是一行行的数据，layui可以支持将行数据转换为树结构
     * @return
     */
    @ApiOperation(value = "获取所有的菜单权限数据列表")
    @MyLog(title = "组织管理-菜单权限管理", action = "获取所有的菜单权限数据列表")
    @RequiresPermissions("sys:permission:list")
    @GetMapping(value = "/permissions")
    public DataResult<List<SysPermission>> getAllPermission() {
        DataResult result = DataResult.success();
        result.setData(permissionService.getAllPermissions());
        return result;
    }

    /**
     * layui的前端中：在权限菜单管理页面，点击新增后，点击所属菜单，展示的菜单权限树
     * 获取目录、菜单（页面）的权限数据。
     * @return
     */
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

    /**
     * layui的前端中：角色管理页面，点击新增角色时，同时赋予权限。
     * 获取所有菜单、权限（页面）、按钮（CRUD），给角色分配菜单权限
     * （其实也可以改造成，新增只新增角色，绑定菜单可以在角色管理页面的分配菜单那里进行分配。或者两个都支持也可以）
     * @return
     */
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

