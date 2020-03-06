package com.feng.companyframe.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: PermissionRespNodeVO
 * @Description： 权限树 节点信息
 * @createTime: 2020/2/11 12:28
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class PermissionRespNodeVO {

    @ApiModelProperty(value = "权限主键id")
    private String id;

    @ApiModelProperty(value = "跳转地址") // 记住即可
    private String url;

    @ApiModelProperty(value = "菜单权限名称")
    private String title;

    @ApiModelProperty(value = "子集集合")   //纳闷
    private List<?> children;

// 后增，在做新增权限树时，新增以下两个字段

    @ApiModelProperty(value = "默认展开所有表格的下级表格")
    private boolean spread=true;

    // layui 树组件的开发，其属性，是否被选中
    @ApiModelProperty(value = "节点是否选中 默认false")
    private boolean checked;
}

