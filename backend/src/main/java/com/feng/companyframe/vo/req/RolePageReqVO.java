package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: RolePageReqVO
 * @Description： 描述
 * @createTime: 2020/2/17 18:19
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePageReqVO {

    @ApiModelProperty(value = "第几页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "当前页的记录数")
    private Integer pageSize = 10;

// 以下為 后增加的 条件查询

    @ApiModelProperty(value = "角色Id")
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色状态")
    private String status;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;


}

