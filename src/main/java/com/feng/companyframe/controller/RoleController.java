package com.feng.companyframe.controller;

import com.feng.companyframe.aop.annotation.MyLog;
import com.feng.companyframe.bean.SysRole;
import com.feng.companyframe.services.RoleService;
import com.feng.companyframe.utils.DataResult;
import com.feng.companyframe.vo.req.RoleAddReqVO;
import com.feng.companyframe.vo.req.RolePageReqVO;
import com.feng.companyframe.vo.req.RoleUpdateReqVO;
import com.feng.companyframe.vo.resp.PageRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName: RoleController
 * @Description： 描述
 * @createTime: 2020/2/17 18:42
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Api(tags = "组织管理-角色管理", value = "角色管理相关接口")
@RestController
@RequestMapping("/api")
public class RoleController {

    @Resource
    private RoleService roleService;

    @ApiOperation(value = "分页获取角色数据")
    @MyLog(title = "组织管理-角色管理",action = "分页获取角色数据")
    @RequiresPermissions("sys:role:list")
    @PostMapping(value = "/roles")
    public DataResult<PageRespVO<SysRole>> pageInfo(@RequestBody RolePageReqVO rolePageReqVO){
        DataResult result = DataResult.success();
        result.setData(roleService.pageInfo(rolePageReqVO));
        return result;
    }

    @ApiOperation(value = "新增角色接口")
    @MyLog(title = "组织管理-角色管理",action = "新增角色接口")
    @RequiresPermissions("sys:role:add")
    @PostMapping(value = "/addRole")
    public DataResult<SysRole> addRole(@RequestBody @Valid RoleAddReqVO roleAddReqVO){
        DataResult result = DataResult.success();
        result.setData(roleService.addRole(roleAddReqVO));
        return result;
    }

    @ApiOperation(value = "获取角色详情接口")
    @MyLog(title = "组织管理-角色管理",action = "获取角色详情接口")
    @RequiresPermissions("sys:role:detail")
    @GetMapping(value = "/role/{id}")
    public DataResult<SysRole> getDetailInfoByRoleId(@PathVariable("id") String roleId){
        DataResult result = DataResult.success();
        SysRole detailInfoByRoleId = roleService.getDetailInfoByRoleId(roleId);
        result.setData(detailInfoByRoleId);
        return result;
    }

    @ApiOperation(value = "更新角色信息接口--需要刷新token--设计到角色、权限、用户三表")
    @MyLog(title = "组织管理-角色管理",action = "更新角色信息接口--需要刷新token--设计到角色、权限、用户三表")
    @RequiresPermissions("sys:role:update")
    @PutMapping(value = "/updateRole")
    public DataResult updateRole(@RequestBody @Valid RoleUpdateReqVO vo){
        roleService.updateRole(vo);
        return DataResult.success();
    }

    @ApiOperation(value = "删除角色信息接口--需要刷新token--设计到角色、权限、用户三表")
    @MyLog(title = "组织管理-角色管理",action = "删除角色信息接口--需要刷新token--设计到角色、权限、用户三表")
    @RequiresPermissions("sys:role:delete")
    @DeleteMapping(value = "/deleteRole/{id}")
    public DataResult deleteRole(@PathVariable("id") String roleId){
        roleService.deletedRoleByRoleId(roleId);
        return DataResult.success();
    }


}

