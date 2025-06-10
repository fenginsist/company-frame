package com.feng.companyframe.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
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

    @ApiModelProperty(value = "菜单权限编码")
    private String code;

    @ApiModelProperty(value = "菜单权限名称")
    private String name;

    @ApiModelProperty(value = "授权(如：sys:user:add)")
    private String perms;

    @ApiModelProperty(value = "跳转地址") // 记住即可
    private String url;

    @ApiModelProperty(value = "资源请求类型")
    private String method;

    @ApiModelProperty(value = "父级菜单权限id")
    private String pid;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "菜单权限类型(1:目录;2:菜单;3:按钮)")
    private Integer type;

    @ApiModelProperty(value = "状态1:正常 0：禁用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新时间")
    private Integer deleted;

    @ApiModelProperty(value = "父级菜单权限名称")
    private String pidName;

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

