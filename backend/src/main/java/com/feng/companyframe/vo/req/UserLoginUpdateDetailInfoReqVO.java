package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * @ClassName: UserLoginUpdateDetailInfoReqVO
 * @Description： 描述
 * @createTime: 2020/2/29 14:19
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class UserLoginUpdateDetailInfoReqVO {

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别(1.男 2.女)")
    private Integer sex;

    @ApiModelProperty(value = "真实名称")
    private String realName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "账户状态 1；正常， 2：锁定")
    private Integer status;

    public UserLoginUpdateDetailInfoReqVO() {
    }

    public UserLoginUpdateDetailInfoReqVO(String email, Integer sex, String realName, String phone, Integer status) {
        this.email = email;
        this.sex = sex;
        this.realName = realName;
        this.phone = phone;
        this.status = status;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginUpdateDetailInfoReqVO that = (UserLoginUpdateDetailInfoReqVO) o;
        return Objects.equals(email, that.email)
                && Objects.equals(sex, that.sex)
                && Objects.equals(realName, that.realName)
                && Objects.equals(phone, that.phone)
                && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, sex, realName, phone, status);
    }

    @Override
    public String toString() {
        return "UserLoginUpdateDetailInfoReqVO{" +
                "email='" + email + '\'' +
                ", sex=" + sex +
                ", realName='" + realName + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
