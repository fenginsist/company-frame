package com.feng.companyframe.controller;

import com.feng.companyframe.aop.annotation.MyLog;
import com.feng.companyframe.services.RolePermissionService;
import com.feng.companyframe.utils.DataResult;
import com.feng.companyframe.vo.req.RolePermissionOperationReqVO;
import com.feng.companyframe.vo.req.RolePermissionReqVO;
import com.feng.companyframe.vo.req.UserOwnRoleReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: RoleController
 * @Description： 描述
 * @createTime: 2025/6/10 16:35
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Api(tags = "赋予权限-角色权限（角色菜单）")
@RestController
@RequestMapping("/api")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @ApiOperation(value = "保存角色拥有的菜单权限信息接口，先删除原有的，在添加新的：菜单权限信息")
    @MyLog(title = "赋予权限操作-角色信息",action = "保存角色拥有的菜单权限信息接口，先删除原有的，在添加新的：菜单权限信息")
    @RequiresPermissions("sys:role:permission:save")
    @PutMapping("/role/permission/save")
    public DataResult saveRolePermission(@RequestBody @Valid RolePermissionReqVO vo) {
        if (vo.getPermissionIds() != null && vo.getPermissionIds().size() > 0) {
            RolePermissionOperationReqVO rolePermissionOperationReqVO = new RolePermissionOperationReqVO();
            rolePermissionOperationReqVO.setRoleId(vo.getRoleId());
            rolePermissionOperationReqVO.setPermissionIds(vo.getPermissionIds());
            rolePermissionService.addRolePermission(rolePermissionOperationReqVO);
        }
        DataResult result = DataResult.success();
        return result;
    }

    @ApiOperation(value = "获取菜单权限id列表：根据角色id获取菜单权限数据，操作role_permission表")
    @MyLog(title = "获取菜单权限列表-根据角色ID",action = "根据角色id获取菜单权限数据，操作role_permission表")
    @RequiresPermissions("sys:role:permission:getPermissionByRoleId")
    @PutMapping("/role/permission/getPermissionsByRoleId")
    public DataResult getPermissionByRoleId(@RequestParam(required = true) String roleId) {
//        userService.setUserOwnRole(vo);
        // 从角色权限中 根据角色id集合 获取 权限id集合
        List<String> permissionIds = rolePermissionService.getPermissionIdsByRoleId(roleId);
        DataResult result = DataResult.success();
        result.setData(permissionIds);
        return result;
    }
}
