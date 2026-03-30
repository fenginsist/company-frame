package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: PermissionUpdateReqVO
 * @Description： 描述
 * @createTime: 2020/2/24 17:43
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class PermissionUpdateReqVO {
    @ApiModelProperty(value = "id")
    @NotBlank(message = "id 不能为空")
    private String id;

    @ApiModelProperty(value = "状态1:正常 0：禁用")
    private Integer status;

    @ApiModelProperty(value = "菜单权限名称")
    private String name;

    @ApiModelProperty(value = "菜单权限标识，shiro 适配restful")
    private String perms;

    @ApiModelProperty(value = "接口地址")
    private String url;

    @ApiModelProperty(value = "请求方式 和url 配合使用 (我们用 路径匹配的方式做权限管理的时候用到)")
    private String method;

    @ApiModelProperty(value = "父级id")
    private String pid;

    @ApiModelProperty(value = "排序码")
    private Integer orderNum;

    @ApiModelProperty(value = "菜单权限类型(1:目录;2:菜单;3:按钮)")
    private Integer type;

}

