package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import javafx.beans.NamedArg;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @ClassName: UserOwnRoleReqVO
 * @Description： 描述
 * @createTime: 2020/2/20 20:04
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class UserOwnRoleReqVO {

    @ApiModelProperty(value = "用户id")
    @NotBlank(message = "用户id不能为空")
    private String userId;

    @ApiModelProperty(value = "角色id集合")
    @NotEmpty(message = "角色id集合不能为空")
    private List<String> roleIds;
}

