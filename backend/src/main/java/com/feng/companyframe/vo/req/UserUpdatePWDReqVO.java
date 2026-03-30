package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * @ClassName: UserUpdatePWDReqVO
 * @Description： 描述
 * @createTime: 2020/2/29 17:50
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class UserUpdatePWDReqVO {

    @ApiModelProperty(value = "旧密码")
    private String oldPwd;

    @ApiModelProperty(value = "新密码")
    private String newPwd;

    public UserUpdatePWDReqVO() {
    }

    public UserUpdatePWDReqVO(String oldPwd, String newPwd) {
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUpdatePWDReqVO that = (UserUpdatePWDReqVO) o;
        return Objects.equals(oldPwd, that.oldPwd) && Objects.equals(newPwd, that.newPwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oldPwd, newPwd);
    }

    @Override
    public String toString() {
        return "UserUpdatePWDReqVO{" +
                "oldPwd='" + oldPwd + '\'' +
                ", newPwd='" + newPwd + '\'' +
                '}';
    }
}
