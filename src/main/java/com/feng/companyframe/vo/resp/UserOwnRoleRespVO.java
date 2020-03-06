package com.feng.companyframe.vo.resp;

import com.feng.companyframe.bean.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: UserOwnRoleRespVO
 * @Description： 用户管理中 用户与角色的联合
 * @createTime: 2020/2/19 22:48
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class UserOwnRoleRespVO {

    @ApiModelProperty("用户的 所有角色集合")
    private List<SysRole> allRole;

    @ApiModelProperty(value = "用户所拥有 角色id 集合")
    private List<String> ownRoles;

}

