package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * @ClassName: PermissionUpdateReqVO
 * @Description： 描述
 * @createTime: 2020/2/24 17:43
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class PermissionUpdateReqVO {
    @ApiModelProperty(value = "id")
    @NotBlank(message = "id 不能为空")
    private String id;

    @ApiModelProperty(value = "状态1:正常 0：禁用")
    private Integer status;

    @ApiModelProperty(value = "菜单权限名称")
    private String name;

    @ApiModelProperty(value = "菜单权限标识，shiro 适配restful")
    private String perms;

    @ApiModelProperty(value = "接口地址")
    private String url;

    @ApiModelProperty(value = "请求方式 和url 配合使用 (我们用 路径匹配的方式做权限管理的时候用到)")
    private String method;

    @ApiModelProperty(value = "父级id")
    private String pid;

    @ApiModelProperty(value = "排序码")
    private Integer orderNum;

    @ApiModelProperty(value = "菜单权限类型(1:目录;2:菜单;3:按钮)")
    private Integer type;

    public PermissionUpdateReqVO() {
    }

    public PermissionUpdateReqVO(String id, Integer status, String name, String perms, String url, String method, String pid, Integer orderNum, Integer type) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.perms = perms;
        this.url = url;
        this.method = method;
        this.pid = pid;
        this.orderNum = orderNum;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionUpdateReqVO that = (PermissionUpdateReqVO) o;
        return Objects.equals(id, that.id)
                && Objects.equals(status, that.status)
                && Objects.equals(name, that.name)
                && Objects.equals(perms, that.perms)
                && Objects.equals(url, that.url)
                && Objects.equals(method, that.method)
                && Objects.equals(pid, that.pid)
                && Objects.equals(orderNum, that.orderNum)
                && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, name, perms, url, method, pid, orderNum, type);
    }

    @Override
    public String toString() {
        return "PermissionUpdateReqVO{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", perms='" + perms + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", pid='" + pid + '\'' +
                ", orderNum=" + orderNum +
                ", type=" + type +
                '}';
    }
}
