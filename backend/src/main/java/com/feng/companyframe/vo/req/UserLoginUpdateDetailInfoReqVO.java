package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: UserLoginUpdateDetailInfoReqVO
 * @Description： 描述
 * @createTime: 2020/2/29 14:19
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class UserLoginUpdateDetailInfoReqVO {

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别(1.男 2.女)")
    private Integer sex;

    @ApiModelProperty(value = "真实名称")
    private String realName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "账户状态 1；正常， 2：锁定")
    private Integer status;

}

