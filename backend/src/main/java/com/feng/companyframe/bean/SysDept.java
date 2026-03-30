package com.feng.companyframe.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class SysDept implements Serializable {
    private String id;

    private String deptNo;

    private String name;

    private String pid;

    private Integer status;

    private String relationCode;

    private String deptManagerId;

    private String managerName;

    private String phone;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private static final long serialVersionUID = 1L;

    /**
     * 新增 父级部门名称
     */
    private String pidName;

    public SysDept() {
    }

    public SysDept(String id, String deptNo, String name, String pid, Integer status, String relationCode, String deptManagerId, String managerName, String phone, Date createTime, Date updateTime, Integer deleted, String pidName) {
        this.id = id;
        this.deptNo = deptNo;
        this.name = name;
        this.pid = pid;
        this.status = status;
        this.relationCode = relationCode;
        this.deptManagerId = deptManagerId;
        this.managerName = managerName;
        this.phone = phone;
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

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }

    public String getDeptManagerId() {
        return deptManagerId;
    }

    public void setDeptManagerId(String deptManagerId) {
        this.deptManagerId = deptManagerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        SysDept sysDept = (SysDept) o;
        return Objects.equals(id, sysDept.id)
                && Objects.equals(deptNo, sysDept.deptNo)
                && Objects.equals(name, sysDept.name)
                && Objects.equals(pid, sysDept.pid)
                && Objects.equals(status, sysDept.status)
                && Objects.equals(relationCode, sysDept.relationCode)
                && Objects.equals(deptManagerId, sysDept.deptManagerId)
                && Objects.equals(managerName, sysDept.managerName)
                && Objects.equals(phone, sysDept.phone)
                && Objects.equals(createTime, sysDept.createTime)
                && Objects.equals(updateTime, sysDept.updateTime)
                && Objects.equals(deleted, sysDept.deleted)
                && Objects.equals(pidName, sysDept.pidName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deptNo, name, pid, status, relationCode, deptManagerId, managerName, phone, createTime, updateTime, deleted, pidName);
    }

    @Override
    public String toString() {
        return "SysDept{" +
                "id='" + id + '\'' +
                ", deptNo='" + deptNo + '\'' +
                ", name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                ", status=" + status +
                ", relationCode='" + relationCode + '\'' +
                ", deptManagerId='" + deptManagerId + '\'' +
                ", managerName='" + managerName + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                ", pidName='" + pidName + '\'' +
                '}';
    }
}
