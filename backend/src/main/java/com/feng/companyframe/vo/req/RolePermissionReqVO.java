package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: RolePermissionReqVO
 * @Description： 描述
 * @createTime: 2025/6/11 17:14
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class RolePermissionReqVO {

    @ApiModelProperty(value = "角色id")
    @NotBlank(message = "角色id不能为空")
    private String roleId;

    @ApiModelProperty(value = "菜单权限id集合")
    @NotEmpty(message = "菜单权限id集合不能为空")
    private List<String> permissionIds;

    public RolePermissionReqVO() {
    }

    public RolePermissionReqVO(String roleId, List<String> permissionIds) {
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
        RolePermissionReqVO that = (RolePermissionReqVO) o;
        return Objects.equals(roleId, that.roleId) && Objects.equals(permissionIds, that.permissionIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, permissionIds);
    }

    @Override
    public String toString() {
        return "RolePermissionReqVO{" +
                "roleId='" + roleId + '\'' +
                ", permissionIds=" + permissionIds +
                '}';
    }
}
