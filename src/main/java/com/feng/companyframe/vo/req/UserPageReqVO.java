package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: UserPageReqVO
 * @Description： 接收分页数据条件 VO
 * @createTime: 2020/2/5 17:45
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class UserPageReqVO {

    @ApiModelProperty(value = "当前第几页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "当前页数量")
    private Integer pageSize = 10;

    /*
    * 以下为 后添加，多条件查询参数
    * */
    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "账户状态(1.正常 2.锁定 ")
    private Integer status;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;
}

