package com.feng.companyframe.vo.resp;

import java.io.Serializable;
import java.util.Objects;

public class UserRespVO implements Serializable {
    private String id;

    private String avatar;

    private String username;

    private String salt;

    private String password;

    private String phone;

    private String deptId;

    private String realName;

    private String nickName;

    private String email;

    private Integer status;

    private Integer sex;

    private Integer deleted;

    private String createId;

    private String updateId;

    private Integer createWhere;

    private String createTime;

    private String updateTime;

    // 后添加
    private String deptName;

    private static final long serialVersionUID = 1L;

    public UserRespVO() {
    }

    public UserRespVO(String id, String avatar, String username, String salt, String password, String phone, String deptId, String realName, String nickName, String email, Integer status, Integer sex, Integer deleted, String createId, String updateId, Integer createWhere, String createTime, String updateTime, String deptName) {
        this.id = id;
        this.avatar = avatar;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.phone = phone;
        this.deptId = deptId;
        this.realName = realName;
        this.nickName = nickName;
        this.email = email;
        this.status = status;
        this.sex = sex;
        this.deleted = deleted;
        this.createId = createId;
        this.updateId = updateId;
        this.createWhere = createWhere;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deptName = deptName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Integer getCreateWhere() {
        return createWhere;
    }

    public void setCreateWhere(Integer createWhere) {
        this.createWhere = createWhere;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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
        UserRespVO userRespVO = (UserRespVO) o;
        return Objects.equals(id, userRespVO.id)
                && Objects.equals(avatar, userRespVO.avatar)
                && Objects.equals(username, userRespVO.username)
                && Objects.equals(salt, userRespVO.salt)
                && Objects.equals(password, userRespVO.password)
                && Objects.equals(phone, userRespVO.phone)
                && Objects.equals(deptId, userRespVO.deptId)
                && Objects.equals(realName, userRespVO.realName)
                && Objects.equals(nickName, userRespVO.nickName)
                && Objects.equals(email, userRespVO.email)
                && Objects.equals(status, userRespVO.status)
                && Objects.equals(sex, userRespVO.sex)
                && Objects.equals(deleted, userRespVO.deleted)
                && Objects.equals(createId, userRespVO.createId)
                && Objects.equals(updateId, userRespVO.updateId)
                && Objects.equals(createWhere, userRespVO.createWhere)
                && Objects.equals(createTime, userRespVO.createTime)
                && Objects.equals(updateTime, userRespVO.updateTime)
                && Objects.equals(deptName, userRespVO.deptName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatar, username, salt, password, phone, deptId, realName, nickName, email, status, sex, deleted, createId, updateId, createWhere, createTime, updateTime, deptName);
    }

    @Override
    public String toString() {
        return "UserRespVO{" +
                "id='" + id + '\'' +
                ", avatar='" + avatar + '\'' +
                ", username='" + username + '\'' +
                ", salt='" + salt + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", deptId='" + deptId + '\'' +
                ", realName='" + realName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", sex=" + sex +
                ", deleted=" + deleted +
                ", createId='" + createId + '\'' +
                ", updateId='" + updateId + '\'' +
                ", createWhere=" + createWhere +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
