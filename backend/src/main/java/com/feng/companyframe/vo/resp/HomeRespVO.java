package com.feng.companyframe.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: HomeRespVO
 * @Description： 响应 菜单 信息, 菜单权限树 包装成的 POJO 返回到前端
 *                包装类
 *                包含 用户信息和权限信息（菜单）
 * @createTime: 2020/2/11 12:24
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
public class HomeRespVO {

    @ApiModelProperty(value = "用户信息")
    private UserInfoRespVO userInfoVO;

    @ApiModelProperty(value = "首页菜单导航数据")
    private List<PermissionRespNodeVO> menus;
}

