package com.feng.companyframe.vo.resp;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

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
public class HomeRespVO {

    @ApiModelProperty(value = "用户信息")
    private UserInfoRespVO userInfoVO;

    @ApiModelProperty(value = "首页菜单导航数据")
    private List<PermissionRespNodeVO> menus;

    public HomeRespVO() {
    }

    public HomeRespVO(UserInfoRespVO userInfoVO, List<PermissionRespNodeVO> menus) {
        this.userInfoVO = userInfoVO;
        this.menus = menus;
    }

    public UserInfoRespVO getUserInfoVO() {
        return userInfoVO;
    }

    public void setUserInfoVO(UserInfoRespVO userInfoVO) {
        this.userInfoVO = userInfoVO;
    }

    public List<PermissionRespNodeVO> getMenus() {
        return menus;
    }

    public void setMenus(List<PermissionRespNodeVO> menus) {
        this.menus = menus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeRespVO that = (HomeRespVO) o;
        return Objects.equals(userInfoVO, that.userInfoVO) && Objects.equals(menus, that.menus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userInfoVO, menus);
    }

    @Override
    public String toString() {
        return "HomeRespVO{" +
                "userInfoVO=" + userInfoVO +
                ", menus=" + menus +
                '}';
    }
}
