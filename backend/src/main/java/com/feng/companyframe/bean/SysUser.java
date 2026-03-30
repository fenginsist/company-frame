package com.feng.companyframe.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class SysUser implements Serializable {
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

    private Integer userType;

    private String createId;

    private String updateId;

    private Integer createWhere;

    private Date createTime;

    private Date updateTime;

    // 后添加
    private String deptName;

    private static final long serialVersionUID = 1L;

    public SysUser() {
    }

    public SysUser(String id, String avatar, String username, String salt, String password, String phone, String deptId, String realName, String nickName, String email, Integer status, Integer sex, Integer deleted, Integer userType, String createId, String updateId, Integer createWhere, Date createTime, Date updateTime, String deptName) {
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
        this.userType = userType;
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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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
        SysUser sysUser = (SysUser) o;
        return Objects.equals(id, sysUser.id)
                && Objects.equals(avatar, sysUser.avatar)
                && Objects.equals(username, sysUser.username)
                && Objects.equals(salt, sysUser.salt)
                && Objects.equals(password, sysUser.password)
                && Objects.equals(phone, sysUser.phone)
                && Objects.equals(deptId, sysUser.deptId)
                && Objects.equals(realName, sysUser.realName)
                && Objects.equals(nickName, sysUser.nickName)
                && Objects.equals(email, sysUser.email)
                && Objects.equals(status, sysUser.status)
                && Objects.equals(sex, sysUser.sex)
                && Objects.equals(deleted, sysUser.deleted)
                && Objects.equals(userType, sysUser.userType)
                && Objects.equals(createId, sysUser.createId)
                && Objects.equals(updateId, sysUser.updateId)
                && Objects.equals(createWhere, sysUser.createWhere)
                && Objects.equals(createTime, sysUser.createTime)
                && Objects.equals(updateTime, sysUser.updateTime)
                && Objects.equals(deptName, sysUser.deptName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatar, username, salt, password, phone, deptId, realName, nickName, email, status, sex, deleted, userType, createId, updateId, createWhere, createTime, updateTime, deptName);
    }

    @Override
    public String toString() {
        return "SysUser{" +
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
                ", userType=" + userType +
                ", createId='" + createId + '\'' +
                ", updateId='" + updateId + '\'' +
                ", createWhere=" + createWhere +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
