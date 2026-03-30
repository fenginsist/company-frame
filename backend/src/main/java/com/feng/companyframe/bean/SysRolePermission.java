package com.feng.companyframe.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class SysRolePermission implements Serializable {
    private String id;

    private String roleId;

    private String permissionId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public SysRolePermission() {
    }

    public SysRolePermission(String id, String roleId, String permissionId, Date createTime) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRolePermission that = (SysRolePermission) o;
        return Objects.equals(id, that.id)
                && Objects.equals(roleId, that.roleId)
                && Objects.equals(permissionId, that.permissionId)
                && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, permissionId, createTime);
    }

    @Override
    public String toString() {
        return "SysRolePermission{" +
                "id='" + id + '\'' +
                ", roleId='" + roleId + '\'' +
                ", permissionId='" + permissionId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
