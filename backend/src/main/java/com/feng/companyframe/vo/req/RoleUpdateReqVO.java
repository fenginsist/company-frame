package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName: RoleUpdateReqVO
 * @Description： 角色编辑 view object
 * @createTime: 2020/2/27 12:39
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class RoleUpdateReqVO {

    @ApiModelProperty(value = "角色id")
    @NotBlank(message = "角色id 不能为空")
    private String id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "状态(1:正常0:弃用)")
    private Integer status;

    @ApiModelProperty(value = "所拥有的菜单权限")
    private List<String> permissionIds;
}

