package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * @ClassName: LogPageReqVO
 * @Description： 日志分页 请求类
 * @createTime: 2020/2/28 19:09
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class LogPageReqVO {

    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize = 10;

    /*
    * 以下为条件查询参数
    * */
    @ApiModelProperty(value = "用户操作动作")
    private String operation;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    public LogPageReqVO() {
    }

    public LogPageReqVO(int pageNum, int pageSize, String operation, String userId, String username, String startTime, String endTime) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.operation = operation;
        this.userId = userId;
        this.username = username;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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
        LogPageReqVO that = (LogPageReqVO) o;
        return pageNum == that.pageNum
                && pageSize == that.pageSize
                && Objects.equals(operation, that.operation)
                && Objects.equals(userId, that.userId)
                && Objects.equals(username, that.username)
                && Objects.equals(startTime, that.startTime)
                && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNum, pageSize, operation, userId, username, startTime, endTime);
    }

    @Override
    public String toString() {
        return "LogPageReqVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", operation='" + operation + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
