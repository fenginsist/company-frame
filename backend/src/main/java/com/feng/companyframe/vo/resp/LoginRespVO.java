package com.feng.companyframe.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * @ClassName: LoginRespVO
 * @Description： 登录成功 返回的 数据
 * @createTime: 2020/2/4 14:49
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class LoginRespVO {

    @ApiModelProperty(value = "正常的业务token")
    private String accessToken;

    @ApiModelProperty(value = "刷新token")
    private String refreshToken;

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "用户名")
    private String username;

    public LoginRespVO() {
    }

    public LoginRespVO(String accessToken, String refreshToken, String id, String phone, String username) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.phone = phone;
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginRespVO that = (LoginRespVO) o;
        return Objects.equals(accessToken, that.accessToken) && Objects.equals(refreshToken, that.refreshToken) && Objects.equals(id, that.id) && Objects.equals(phone, that.phone) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, refreshToken, id, phone, username);
    }

    @Override
    public String toString() {
        return "LoginRespVO{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
