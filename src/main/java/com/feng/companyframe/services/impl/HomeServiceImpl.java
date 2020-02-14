package com.feng.companyframe.services.impl;

import com.alibaba.fastjson.JSON;
import com.feng.companyframe.bean.SysUser;
import com.feng.companyframe.mapper.SysUserMapper;
import com.feng.companyframe.services.HomeService;
import com.feng.companyframe.vo.resp.HomeRespVO;
import com.feng.companyframe.vo.resp.PermissionRespNodeVO;
import com.feng.companyframe.vo.resp.UserInfoRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: HomeServiceImpl
 * @Description： 描述
 * @createTime: 2020/2/11 12:31
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public HomeRespVO getHome(String userId) {
        HomeRespVO homeRespVO = new HomeRespVO();
        // 假数据
        //String home="[{\"children\":[{\"children\":[{\"children\":[{\"children\":[{\"children\":[],\"id\":\"6\",\"title\":\"五级类目5-6\",\"url\":\"string\"}],\"id\":\"5\",\"title\":\"四级类目4-5\",\"url\":\"string\"}],\"id\":\"4\",\"title\":\"三级类目3-4\",\"url\":\"string\"}],\"id\":\"3\",\"title\":\"二级类目2-3\",\"url\":\"string\"}],\"id\":\"1\",\"title\":\"类目1\",\"url\":\"string\"},{\"children\":[],\"id\":\"2\",\"title\":\"类目2\",\"url\":\"string\"}]";
        String home="[\n" +
                " {\n" +
                " \"children\": [\n" +
                " {\n" +
                " \"children\": [],\n" +
                " \"id\": \"3\",\n" +
                " \"title\": \"菜单权限管理\",\n" +
                " \"url\": \"/index/menus\"\n" +
                " }\n" +
                " ],\n" +
                " \"id\": \"1\",\n" +
                " \"title\": \"组织管理\",\n" +
                " \"url\": \"string\"\n" +
                " },\n" +
                " {\n" +
                " \"children\": [],\n" +
                " \"id\": \"2\",\n" +
                " \"title\": \"类目2\",\n" +
                " \"url\": \"string\"\n" +
                " }\n" +
                "]";

        List<PermissionRespNodeVO> list = JSON.parseArray(home, PermissionRespNodeVO.class);
        // 设置菜单数据
        homeRespVO.setMenus(list);
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        UserInfoRespVO userInfoRespVO = new UserInfoRespVO();
        if (null != userInfoRespVO){
            // 复制 实体类 的数据
            BeanUtils.copyProperties(sysUser, userInfoRespVO);
            userInfoRespVO.setDeptName("test部门");
        }
        // 设置用户数据
        homeRespVO.setUserInfoVO(userInfoRespVO);
        return homeRespVO;
    }
}

