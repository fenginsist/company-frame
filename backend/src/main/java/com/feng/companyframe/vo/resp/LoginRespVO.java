package com.feng.companyframe.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: LoginRespVO
 * @Description： 登录成功 返回的 数据
 * @createTime: 2020/2/4 14:49
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class LoginRespVO {

    @ApiModelProperty(value = "正常的业务token")
    private String accessToken;

    @ApiModelProperty(value = "刷新token")
    private String refreshToken;

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "用户名")
    private String username;
}

