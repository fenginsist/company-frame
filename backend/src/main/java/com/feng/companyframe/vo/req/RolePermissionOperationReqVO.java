package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName: RolePermissionOperationReqVO
 * @Description： 对应的是 角色-权限表的 实体类
 * @createTime: 2020/2/18 14:17
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class RolePermissionOperationReqVO {

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "菜单权限集合")
    private List<String> permissionIds;

    public RolePermissionOperationReqVO() {
    }

    public RolePermissionOperationReqVO(String roleId, List<String> permissionIds) {
        this.roleId = roleId;
        this.permissionIds = permissionIds;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<String> permissionIds) {
        this.permissionIds = permissionIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePermissionOperationReqVO that = (RolePermissionOperationReqVO) o;
        return Objects.equals(roleId, that.roleId) && Objects.equals(permissionIds, that.permissionIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, permissionIds);
    }

    @Override
    public String toString() {
        return "RolePermissionOperationReqVO{" +
                "roleId='" + roleId + '\'' +
                ", permissionIds=" + permissionIds +
                '}';
    }
}
