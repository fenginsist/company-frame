package com.feng.companyframe.mapper;

import com.feng.companyframe.bean.SysPermission;
import com.feng.companyframe.bean.SysRolePermission;

import java.util.List;

public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);

    int batchInsertRolePermission(List<SysRolePermission> list);

    /**
     * 根据菜单权限id获取关联的角色id集合
     * @param permissionId
     * @return
     */
    List<String> getRoleIdsByPermissionId(String permissionId);


    /**
     * 通过权限id 删除关联的角色,删除权限id 在 角色权限表中,
     * @param permissionId
     * @return
     */
    int removeByPermissionId(String permissionId);

    /**
     * 根据角色id获取该角色关联的菜单权限id集合
     * @param roleId
     * @return
     */
    List<String> getPermissionIdsByRoleId(String roleId);


    /**
     * 根据 角色id 删除角色和菜单权限关联表相关数据
     * @param roleId
     * @return
     */
    int removeByRoleId(String roleId);

    /**
     * 根据角色id集合 获取 权限id集合
     * @param roleIds
     * @return
     */
    List<String> getPermissionIdsByRoleIds(List<String> roleIds);
}