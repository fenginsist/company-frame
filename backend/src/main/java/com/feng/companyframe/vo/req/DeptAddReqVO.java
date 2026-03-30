package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * @ClassName: DeptAddReqVO
 * @Description： 描述
 * @createTime: 2020/2/18 22:24
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class DeptAddReqVO {

    @ApiModelProperty(value = "部门名称")
    @NotBlank(message = "部门名称不能为空")
    private String name;

    @ApiModelProperty(value = "父级id 一级为 0")
    @NotBlank(message = "父级id不能为空")
    private String pid;

    @ApiModelProperty(value = "部门经理名称")
    private String managerName;

    @ApiModelProperty(value = "部门经理电话")
    private String phone;

    @ApiModelProperty(value = "机构状态(1:正常；0:弃用)")
    private Integer status;

    public DeptAddReqVO() {
    }

    public DeptAddReqVO(String name, String pid, String managerName, String phone, Integer status) {
        this.name = name;
        this.pid = pid;
        this.managerName = managerName;
        this.phone = phone;
        this.status = status;
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
        DeptAddReqVO that = (DeptAddReqVO) o;
        return Objects.equals(name, that.name) && Objects.equals(pid, that.pid) && Objects.equals(managerName, that.managerName) && Objects.equals(phone, that.phone) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pid, managerName, phone, status);
    }

    @Override
    public String toString() {
        return "DeptAddReqVO{" +
                "name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                ", managerName='" + managerName + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}

