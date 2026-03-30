package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName: RoleAddReqVO
 * @Description： 添加 角色 的请求 实体类
 * @createTime: 2020/2/18 14:05
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class RoleAddReqVO {

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "状态（1：正常，0：弃用）")
    private Integer status;

    @ApiModelProperty(value = "拥有的权限 id 集合")
    private List<String> permissionIds;

}

