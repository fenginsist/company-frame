package com.feng.companyframe.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class SysLog implements Serializable {
    private String id;

    private String userId;

    private String username;

    private String operation;

    private Integer time;

    private String method;

    private String params;

    private String ip;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public SysLog() {
    }

    public SysLog(String id, String userId, String username, String operation, Integer time, String method, String params, String ip, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.operation = operation;
        this.time = time;
        this.method = method;
        this.params = params;
        this.ip = ip;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysLog sysLog = (SysLog) o;
        return Objects.equals(id, sysLog.id)
                && Objects.equals(userId, sysLog.userId)
                && Objects.equals(username, sysLog.username)
                && Objects.equals(operation, sysLog.operation)
                && Objects.equals(time, sysLog.time)
                && Objects.equals(method, sysLog.method)
                && Objects.equals(params, sysLog.params)
                && Objects.equals(ip, sysLog.ip)
                && Objects.equals(createTime, sysLog.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, username, operation, time, method, params, ip, createTime);
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", operation='" + operation + '\'' +
                ", time=" + time +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", ip='" + ip + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
