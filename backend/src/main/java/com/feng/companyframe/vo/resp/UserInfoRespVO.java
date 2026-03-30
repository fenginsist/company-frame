package com.feng.companyframe.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: UserInfoRespVO
 * @Description： 响应 用户 信息
 * @createTime: 2020/2/11 12:24
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class UserInfoRespVO {

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "所属部门名称")
    private String deptName;
}

