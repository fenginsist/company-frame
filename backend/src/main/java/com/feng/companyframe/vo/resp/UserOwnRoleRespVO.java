package com.feng.companyframe.vo.resp;

import com.feng.companyframe.bean.SysRole;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName: UserOwnRoleRespVO
 * @Description： 用户管理中 用户与角色的联合
 * @createTime: 2020/2/19 22:48
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class UserOwnRoleRespVO {

    @ApiModelProperty("用户的 所有角色集合")
    private List<SysRole> allRole;

    @ApiModelProperty(value = "用户所拥有 角色id 集合")
    private List<String> ownRoles;

    public UserOwnRoleRespVO() {
    }

    public UserOwnRoleRespVO(List<SysRole> allRole, List<String> ownRoles) {
        this.allRole = allRole;
        this.ownRoles = ownRoles;
    }

    public List<SysRole> getAllRole() {
        return allRole;
    }

    public void setAllRole(List<SysRole> allRole) {
        this.allRole = allRole;
    }

    public List<String> getOwnRoles() {
        return ownRoles;
    }

    public void setOwnRoles(List<String> ownRoles) {
        this.ownRoles = ownRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOwnRoleRespVO that = (UserOwnRoleRespVO) o;
        return Objects.equals(allRole, that.allRole) && Objects.equals(ownRoles, that.ownRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allRole, ownRoles);
    }

    @Override
    public String toString() {
        return "UserOwnRoleRespVO{" +
                "allRole=" + allRole +
                ", ownRoles=" + ownRoles +
                '}';
    }
}
