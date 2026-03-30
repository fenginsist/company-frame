package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * @ClassName: UserAddReqVO
 * @Description： 描述
 * @createTime: 2020/2/19 15:43
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class UserAddReqVO {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "账号不能为空")
    private String username;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "创建来源(1.web 2.android 3.ios )")
    private Integer createWhere;

    @ApiModelProperty(value = "所属机构")
    @NotBlank(message = "所属机构不能为空")
    private String deptId;

    @ApiModelProperty(value = "账户状态(1.正常 2.锁定 )")
    private Integer status;

    @ApiModelProperty(value = "用户类型（1:管理员添加，2:用户自己注册）")
    private Integer userType;

    public UserAddReqVO() {
    }

    public UserAddReqVO(String username, String avatar, String password, String phone, String realName, String nickName, String email, Integer sex, Integer createWhere, String deptId, Integer status, Integer userType) {
        this.username = username;
        this.avatar = avatar;
        this.password = password;
        this.phone = phone;
        this.realName = realName;
        this.nickName = nickName;
        this.email = email;
        this.sex = sex;
        this.createWhere = createWhere;
        this.deptId = deptId;
        this.status = status;
        this.userType = userType;
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

    public Integer getCreateWhere() {
        return createWhere;
    }

    public void setCreateWhere(Integer createWhere) {
        this.createWhere = createWhere;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAddReqVO that = (UserAddReqVO) o;
        return Objects.equals(username, that.username)
                && Objects.equals(avatar, that.avatar)
                && Objects.equals(password, that.password)
                && Objects.equals(phone, that.phone)
                && Objects.equals(realName, that.realName)
                && Objects.equals(nickName, that.nickName)
                && Objects.equals(email, that.email)
                && Objects.equals(sex, that.sex)
                && Objects.equals(createWhere, that.createWhere)
                && Objects.equals(deptId, that.deptId)
                && Objects.equals(status, that.status)
                && Objects.equals(userType, that.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, avatar, password, phone, realName, nickName, email, sex, createWhere, deptId, status, userType);
    }

    @Override
    public String toString() {
        return "UserAddReqVO{" +
                "username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", realName='" + realName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", createWhere=" + createWhere +
                ", deptId='" + deptId + '\'' +
                ", status=" + status +
                ", userType=" + userType +
                '}';
    }
}
