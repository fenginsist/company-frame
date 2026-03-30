package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: RoleUpdateReqVO
 * @Description： 角色编辑 view object
 * @createTime: 2020/2/27 12:39
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class RoleUpdateReqVO {

    @ApiModelProperty(value = "角色id")
    @NotBlank(message = "角色id 不能为空")
    private String id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "状态(1:正常0:弃用)")
    private Integer status;

    @ApiModelProperty(value = "所拥有的菜单权限")
    private List<String> permissionIds;

    public RoleUpdateReqVO() {
    }

    public RoleUpdateReqVO(String id, String name, String description, Integer status, List<String> permissionIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.permissionIds = permissionIds;
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
        RoleUpdateReqVO that = (RoleUpdateReqVO) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(status, that.status)
                && Objects.equals(permissionIds, that.permissionIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status, permissionIds);
    }

    @Override
    public String toString() {
        return "RoleUpdateReqVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", permissionIds=" + permissionIds +
                '}';
    }
}
