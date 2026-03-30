package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @ClassName: RolePermissionReqVO
 * @Description： 描述
 * @createTime: 2025/6/11 17:14
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class RolePermissionReqVO {

    @ApiModelProperty(value = "角色id")
    @NotBlank(message = "角色id不能为空")
    private String roleId;

    @ApiModelProperty(value = "菜单权限id集合")
    @NotEmpty(message = "菜单权限id集合不能为空")
    private List<String> permissionIds;
}
