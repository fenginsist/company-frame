package com.feng.companyframe.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * @ClassName: UserInfoRespVO
 * @Description： 响应 用户 信息
 * @createTime: 2020/2/11 12:24
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class UserInfoRespVO {

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "所属部门名称")
    private String deptName;

    public UserInfoRespVO() {
    }

    public UserInfoRespVO(String id, String username, String deptId, String deptName) {
        this.id = id;
        this.username = username;
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoRespVO that = (UserInfoRespVO) o;
        return Objects.equals(id, that.id)
                && Objects.equals(username, that.username)
                && Objects.equals(deptId, that.deptId)
                && Objects.equals(deptName, that.deptName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, deptId, deptName);
    }

    @Override
    public String toString() {
        return "UserInfoRespVO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
