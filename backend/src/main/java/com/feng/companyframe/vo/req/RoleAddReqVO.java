package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: RoleAddReqVO
 * @Description： 添加 角色 的请求 实体类
 * @createTime: 2020/2/18 14:05
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class RoleAddReqVO {

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "状态（1：正常，0：弃用）")
    private Integer status;

    @ApiModelProperty(value = "拥有的权限 id 集合")
    private List<String> permissionIds;

    public RoleAddReqVO() {
    }

    public RoleAddReqVO(String name, String description, Integer status, List<String> permissionIds) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.permissionIds = permissionIds;
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
        RoleAddReqVO that = (RoleAddReqVO) o;
        return Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(status, that.status)
                && Objects.equals(permissionIds, that.permissionIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, status, permissionIds);
    }

    @Override
    public String toString() {
        return "RoleAddReqVO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", permissionIds=" + permissionIds +
                '}';
    }
}
