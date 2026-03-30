package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * @ClassName: UserUpdateReqVO
 * @Description： 描述
 * @createTime: 2020/2/23 21:45
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class UserUpdateReqVO {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户id 不能为空")
    private String id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "状态(1:正常，2：锁定)")
    private Integer status;

    @ApiModelProperty(value = "所属部门")
    private String deptId;

    public UserUpdateReqVO() {
    }

    public UserUpdateReqVO(String id, String username, String avatar, String password, String phone, String realName, String nickName, String email, Integer sex, Integer status, String deptId) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.password = password;
        this.phone = phone;
        this.realName = realName;
        this.nickName = nickName;
        this.email = email;
        this.sex = sex;
        this.status = status;
        this.deptId = deptId;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUpdateReqVO that = (UserUpdateReqVO) o;
        return Objects.equals(id, that.id)
                && Objects.equals(username, that.username)
                && Objects.equals(avatar, that.avatar)
                && Objects.equals(password, that.password)
                && Objects.equals(phone, that.phone)
                && Objects.equals(realName, that.realName)
                && Objects.equals(nickName, that.nickName)
                && Objects.equals(email, that.email)
                && Objects.equals(sex, that.sex)
                && Objects.equals(status, that.status)
                && Objects.equals(deptId, that.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, avatar, password, phone, realName, nickName, email, sex, status, deptId);
    }

    @Override
    public String toString() {
        return "UserUpdateReqVO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", realName='" + realName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", status=" + status +
                ", deptId='" + deptId + '\'' +
                '}';
    }
}
