package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * @ClassName: DeptUpdateReqVO
 * @Description： 描述
 * @createTime: 2020/2/27 23:26
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class DeptUpdateReqVO {

    @ApiModelProperty(value = "部门id")
    @NotBlank(message = "部门id 不能为空")
    private String id;

    @ApiModelProperty(value = "部门呢名称")
    private String name;

    @ApiModelProperty(value = "父级id")
    private String pid;

    @ApiModelProperty(value = "部门状态(1:正常；0:弃用)")
    private Integer status;

    @ApiModelProperty(value = "部门经理名称")
    private String managerName;

    @ApiModelProperty(value = "部门经理电话")
    private String phone;

    public DeptUpdateReqVO() {
    }

    public DeptUpdateReqVO(String id, String name, String pid, Integer status, String managerName, String phone) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.status = status;
        this.managerName = managerName;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptUpdateReqVO that = (DeptUpdateReqVO) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(pid, that.pid)
                && Objects.equals(status, that.status)
                && Objects.equals(managerName, that.managerName)
                && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pid, status, managerName, phone);
    }

    @Override
    public String toString() {
        return "DeptUpdateReqVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                ", status=" + status +
                ", managerName='" + managerName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
