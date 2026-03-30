package com.feng.companyframe.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: DeptRespNodeVO
 * @Description： 部门树的 节点信息
 * @createTime: 2020/2/18 19:42
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class DeptRespNodeVO {

    /**
     * id 很重要，需要告诉后端，选择哪个
     */
    @ApiModelProperty(value = "部门id")
    private String id;

    @ApiModelProperty(value = "部门名称")
    private String title;

    @ApiModelProperty(value = "是否展开 默认展开(true)")
    private boolean spread = true;

    @ApiModelProperty(value = "子集叶子节点")
    private List<?> children;
}

