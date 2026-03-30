package com.feng.companyframe.mapper;

import com.feng.companyframe.bean.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    /**
     * 根据用户  查询关联的  角色id集合
     * @param userId
     * @return
     */
    List<String> getRoleIdsByUserId(String userId);


// 以下两个方法 一块用
    /**
     * 根据用户id 删除和该用户关联的角色关联表数据
     * @param userId
     * @return
     */
    int removeByUserId(String userId);

    /**
     * 批量插入用户和角色关联数据
     * @param list
     * @return
     */
    int batchUserRole(List<SysUserRole> list);

    /**
     * 根据角色id集合获取所有关联用户di集合
     * @param roleIds
     * @return
     */
    List<String> getUserIdsByRoleIds(List<String> roleIds);

    /**
     * 根据角色id 获取 用户id集合
     * @param roleId
     * @return
     */
    List<String> getUserIdsByRoleId(String roleId);

    /**
     * 根据角色id 删除该角色关联菜单权限所有数据
     * @param roleId
     * @return
     */
    int removeByRoleId(String roleId);
}