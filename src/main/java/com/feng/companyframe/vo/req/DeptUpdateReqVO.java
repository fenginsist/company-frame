package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: DeptUpdateReqVO
 * @Description： 描述
 * @createTime: 2020/2/27 23:26
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class DeptUpdateReqVO {

    @ApiModelProperty(value = "部门id")
    @NotBlank(message = "部门id 不能为空")
    private String id;

    @ApiModelProperty(value = "部门呢名称")
    private String name;

    @ApiModelProperty(value = "父级id")
    private String pid;

    @ApiModelProperty(value = "部门状态(1:正常；0:弃用)")
    private Integer status;

    @ApiModelProperty(value = "部门经理名称")
    private String managerName;

    @ApiModelProperty(value = "部门经理电话")
    private String phone;

}
