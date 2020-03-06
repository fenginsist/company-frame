package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: UserUpdateReqVO
 * @Description： 描述
 * @createTime: 2020/2/23 21:45
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class UserUpdateReqVO {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户id 不能为空")
    private String id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "状态(1:正常，2：锁定)")
    private Integer status;

    @ApiModelProperty(value = "所属部门")
    private String deptId;
}

