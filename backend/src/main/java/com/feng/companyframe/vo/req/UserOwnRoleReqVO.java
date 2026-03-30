package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: UserOwnRoleReqVO
 * @Description： 描述
 * @createTime: 2020/2/20 20:04
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class UserOwnRoleReqVO {

    @ApiModelProperty(value = "用户id")
    @NotBlank(message = "用户id不能为空")
    private String userId;

    @ApiModelProperty(value = "角色id集合")
    @NotEmpty(message = "角色id集合不能为空")
    private List<String> roleIds;

    public UserOwnRoleReqVO() {
    }

    public UserOwnRoleReqVO(String userId, List<String> roleIds) {
        this.userId = userId;
        this.roleIds = roleIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOwnRoleReqVO that = (UserOwnRoleReqVO) o;
        return Objects.equals(userId, that.userId) && Objects.equals(roleIds, that.roleIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleIds);
    }

    @Override
    public String toString() {
        return "UserOwnRoleReqVO{" +
                "userId='" + userId + '\'' +
                ", roleIds=" + roleIds +
                '}';
    }
}
