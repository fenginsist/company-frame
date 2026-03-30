package com.feng.companyframe.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: LogPageReqVO
 * @Description： 日志分页 请求类
 * @createTime: 2020/2/28 19:09
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class LogPageReqVO {

    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize = 10;

    /*
    * 以下为条件查询参数
    * */
    @ApiModelProperty(value = "用户操作动作")
    private String operation;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;
}

