package com.feng.companyframe.bean;

import com.feng.companyframe.vo.resp.PermissionRespNodeVO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SysRole implements Serializable {
    private String id;

    private String name;

    private String description;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    // 后新增，为了对角色编辑时的回显
    private List<PermissionRespNodeVO> permissionRespNodeVOS;

    private static final long serialVersionUID = 1L;

    public SysRole() {
    }

    public SysRole(String id, String name, String description, Integer status, Date createTime, Date updateTime, Integer deleted, List<PermissionRespNodeVO> permissionRespNodeVOS) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
        this.permissionRespNodeVOS = permissionRespNodeVOS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public List<PermissionRespNodeVO> getPermissionRespNodeVOS() {
        return permissionRespNodeVOS;
    }

    public void setPermissionRespNodeVOS(List<PermissionRespNodeVO> permissionRespNodeVOS) {
        this.permissionRespNodeVOS = permissionRespNodeVOS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRole sysRole = (SysRole) o;
        return Objects.equals(id, sysRole.id)
                && Objects.equals(name, sysRole.name)
                && Objects.equals(description, sysRole.description)
                && Objects.equals(status, sysRole.status)
                && Objects.equals(createTime, sysRole.createTime)
                && Objects.equals(updateTime, sysRole.updateTime)
                && Objects.equals(deleted, sysRole.deleted)
                && Objects.equals(permissionRespNodeVOS, sysRole.permissionRespNodeVOS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status, createTime, updateTime, deleted, permissionRespNodeVOS);
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                ", permissionRespNodeVOS=" + permissionRespNodeVOS +
                '}';
    }
}
