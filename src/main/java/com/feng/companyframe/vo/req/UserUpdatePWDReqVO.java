package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: UserUpdatePWDReqVO
 * @Description： 描述
 * @createTime: 2020/2/29 17:50
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class UserUpdatePWDReqVO {

    @ApiModelProperty(value = "旧密码")
    private String oldPwd;

    @ApiModelProperty(value = "新密码")
    private String newPwd;
}

