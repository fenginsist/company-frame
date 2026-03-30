package com.feng.companyframe.mapper;

import com.feng.companyframe.bean.SysUser;
import com.feng.companyframe.vo.req.UserPageReqVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    // 自己编写 通过name获取user
    SysUser getUserInfoByName(String name);

    // 查询所有用户
    List<SysUser> getAllSysUser(UserPageReqVO userPageReqVO);

    // 删除 用户
    int deletedUsers(@Param("sysUser") SysUser sysUser, @Param("list") List<String> list);

    /**
     * 根据部门id集合 获取 用户信息
     * @param deptIds
     * @return
     */
    List<SysUser> getUserInfoByDeptIds(List<String> deptIds);


}