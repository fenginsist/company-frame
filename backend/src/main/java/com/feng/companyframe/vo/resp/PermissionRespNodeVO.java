package com.feng.companyframe.vo.resp;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: PermissionRespNodeVO
 * @Description： 权限树 节点信息
 * @createTime: 2020/2/11 12:28
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class PermissionRespNodeVO {

    @ApiModelProperty(value = "权限主键id")
    private String id;

    @ApiModelProperty(value = "菜单权限编码")
    private String code;

    @ApiModelProperty(value = "菜单权限名称")
    private String name;

    @ApiModelProperty(value = "授权(如：sys:user:add)")
    private String perms;

    @ApiModelProperty(value = "跳转地址") // 记住即可
    private String url;

    @ApiModelProperty(value = "资源请求类型")
    private String method;

    @ApiModelProperty(value = "父级菜单权限id")
    private String pid;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "菜单权限类型(1:目录;2:菜单;3:按钮)")
    private Integer type;

    @ApiModelProperty(value = "状态1:正常 0：禁用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新时间")
    private Integer deleted;

    @ApiModelProperty(value = "父级菜单权限名称")
    private String pidName;

    @ApiModelProperty(value = "菜单权限名称")
    private String title;

    @ApiModelProperty(value = "子集集合")   //纳闷
    private List<?> children;

// 后增，在做新增权限树时，新增以下两个字段

    @ApiModelProperty(value = "默认展开所有表格的下级表格")
    private boolean spread=true;

    // layui 树组件的开发，其属性，是否被选中
    @ApiModelProperty(value = "节点是否选中 默认false")
    private boolean checked;

    public PermissionRespNodeVO() {
    }

    public PermissionRespNodeVO(String id, String code, String name, String perms, String url, String method, String pid, Integer orderNum, Integer type, Integer status, Date createTime, Date updateTime, Integer deleted, String pidName, String title, List<?> children, boolean spread, boolean checked) {
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
        this.title = title;
        this.children = children;
        this.spread = spread;
        this.checked = checked;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionRespNodeVO that = (PermissionRespNodeVO) o;
        return spread == that.spread
                && checked == that.checked
                && Objects.equals(id, that.id)
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
                && Objects.equals(pidName, that.pidName)
                && Objects.equals(title, that.title)
                && Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, perms, url, method, pid, orderNum, type, status, createTime, updateTime, deleted, pidName, title, children, spread, checked);
    }

    @Override
    public String toString() {
        return "PermissionRespNodeVO{" +
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
                ", title='" + title + '\'' +
                ", children=" + children +
                ", spread=" + spread +
                ", checked=" + checked +
                '}';
    }
}
