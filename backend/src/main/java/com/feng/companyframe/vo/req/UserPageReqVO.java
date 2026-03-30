package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * @ClassName: UserPageReqVO
 * @Description： 接收分页数据条件 VO
 * @createTime: 2020/2/5 17:45
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class UserPageReqVO {

    @ApiModelProperty(value = "当前第几页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "当前页数量")
    private Integer pageSize = 10;

    /*
    * 以下为 后添加，多条件查询参数
    * */
    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "账户状态(1.正常 2.锁定 ")
    private Integer status;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "用户类型（1:管理员添加，2:用户自己注册）")
    private String userType;

    public UserPageReqVO() {
    }

    public UserPageReqVO(Integer pageNum, Integer pageSize, String userId, String username, String nickName, String phone, Integer status, String startTime, String endTime, String userType) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.userId = userId;
        this.username = username;
        this.nickName = nickName;
        this.phone = phone;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userType = userType;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPageReqVO that = (UserPageReqVO) o;
        return Objects.equals(pageNum, that.pageNum)
                && Objects.equals(pageSize, that.pageSize)
                && Objects.equals(userId, that.userId)
                && Objects.equals(username, that.username)
                && Objects.equals(nickName, that.nickName)
                && Objects.equals(phone, that.phone)
                && Objects.equals(status, that.status)
                && Objects.equals(startTime, that.startTime)
                && Objects.equals(endTime, that.endTime)
                && Objects.equals(userType, that.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNum, pageSize, userId, username, nickName, phone, status, startTime, endTime, userType);
    }

    @Override
    public String toString() {
        return "UserPageReqVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
