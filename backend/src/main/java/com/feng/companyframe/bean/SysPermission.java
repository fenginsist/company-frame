package com.feng.companyframe.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class SysPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String code;

    private String name;

    private String perms;

    private String url;

    private String method;

    private String pid;

    private Integer orderNum;

    private Integer type;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    // 新增的  父级名称
    private String pidName;


    public SysPermission() {
    }

    public SysPermission(String id, String code, String name, String perms, String url, String method, String pid, Integer orderNum, Integer type, Integer status, Date createTime, Date updateTime, Integer deleted, String pidName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.perms = perms;
        this.url = url;
        this.method = method;
        this.pid = pid;
        this.orderNum = orderNum;
        this.type = type;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
        this.pidName = pidName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getPidName() {
        return pidName;
    }

    public void setPidName(String pidName) {
        this.pidName = pidName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysPermission that = (SysPermission) o;
        return Objects.equals(id, that.id)
                && Objects.equals(code, that.code)
                && Objects.equals(name, that.name)
                && Objects.equals(perms, that.perms)
                && Objects.equals(url, that.url)
                && Objects.equals(method, that.method)
                && Objects.equals(pid, that.pid)
                && Objects.equals(orderNum, that.orderNum)
                && Objects.equals(type, that.type)
                && Objects.equals(status, that.status)
                && Objects.equals(createTime, that.createTime)
                && Objects.equals(updateTime, that.updateTime)
                && Objects.equals(deleted, that.deleted)
                && Objects.equals(pidName, that.pidName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, perms, url, method, pid, orderNum, type, status, createTime, updateTime, deleted, pidName);
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", perms='" + perms + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", pid='" + pid + '\'' +
                ", orderNum=" + orderNum +
                ", type=" + type +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                ", pidName='" + pidName + '\'' +
                '}';
    }
}
