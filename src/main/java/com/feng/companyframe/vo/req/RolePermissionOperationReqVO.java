package com.feng.companyframe.vo.req;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.catalina.LifecycleState;

import java.util.List;

/**
 * @ClassName: RolePermissionOperationReqVO
 * @Description： 对应的是 角色-权限表的 实体类
 * @createTime: 2020/2/18 14:17
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class RolePermissionOperationReqVO {

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "菜单权限集合")
    private List<String> permissionIds;
}

