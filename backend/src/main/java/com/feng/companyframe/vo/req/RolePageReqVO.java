package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * @ClassName: RolePageReqVO
 * @Description： 描述
 * @createTime: 2020/2/17 18:19
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class RolePageReqVO {

    @ApiModelProperty(value = "第几页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "当前页的记录数")
    private Integer pageSize = 10;

// 以下為 后增加的 条件查询

    @ApiModelProperty(value = "角色Id")
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色状态")
    private String status;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;


    public RolePageReqVO() {
    }

    public RolePageReqVO(Integer pageNum, Integer pageSize, String roleId, String roleName, String status, String startTime, String endTime) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.roleId = roleId;
        this.roleName = roleName;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePageReqVO that = (RolePageReqVO) o;
        return Objects.equals(pageNum, that.pageNum)
                && Objects.equals(pageSize, that.pageSize)
                && Objects.equals(roleId, that.roleId)
                && Objects.equals(roleName, that.roleName)
                && Objects.equals(status, that.status)
                && Objects.equals(startTime, that.startTime)
                && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNum, pageSize, roleId, roleName, status, startTime, endTime);
    }

    @Override
    public String toString() {
        return "RolePageReqVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", status='" + status + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
