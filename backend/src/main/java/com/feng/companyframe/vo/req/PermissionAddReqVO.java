package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @ClassName: PermissionAddReqVO
 * @Description： 描述
 * @createTime: 2020/2/16 20:12
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class PermissionAddReqVO {
    @ApiModelProperty(value = "菜单权限名称")
    @NotBlank(message = "菜单权限名称不能为空")
    private String name;

    @ApiModelProperty(value = "菜单权限标识，shiro 适配restful")
    private String perms;

    @ApiModelProperty(value = "接口地址")
    private String url;

    @ApiModelProperty(value = "请求方式 和url 配合使用 (我们用 路径匹配的方式做权限管理的时候用到)")
    private String method;

    @ApiModelProperty(value = "父级id")
    @NotNull(message = "所属菜单不能为空")
    private String pid;

    @ApiModelProperty(value = "排序码")
    private Integer orderNum;

    @ApiModelProperty(value = "菜单权限类型(1:目录;2:菜单;3:按钮)")
    @NotNull(message = "菜单权限类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "状态1:正常 0：禁用")
    private Integer status;

    @ApiModelProperty(value = "编码(前后端分离 前段对按钮显示隐藏控制 btn-permission-search 代表 菜单权限管理的列表查询按钮)")
    private String code;

    public PermissionAddReqVO() {
    }

    public PermissionAddReqVO(String name, String perms, String url, String method, String pid, Integer orderNum, Integer type, Integer status, String code) {
        this.name = name;
        this.perms = perms;
        this.url = url;
        this.method = method;
        this.pid = pid;
        this.orderNum = orderNum;
        this.type = type;
        this.status = status;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionAddReqVO that = (PermissionAddReqVO) o;
        return Objects.equals(name, that.name)
                && Objects.equals(perms, that.perms)
                && Objects.equals(url, that.url)
                && Objects.equals(method, that.method)
                && Objects.equals(pid, that.pid)
                && Objects.equals(orderNum, that.orderNum)
                && Objects.equals(type, that.type)
                && Objects.equals(status, that.status)
                && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, perms, url, method, pid, orderNum, type, status, code);
    }

    @Override
    public String toString() {
        return "PermissionAddReqVO{" +
                "name='" + name + '\'' +
                ", perms='" + perms + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", pid='" + pid + '\'' +
                ", orderNum=" + orderNum +
                ", type=" + type +
                ", status=" + status +
                ", code='" + code + '\'' +
                '}';
    }
}
