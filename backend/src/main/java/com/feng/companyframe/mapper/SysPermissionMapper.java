package com.feng.companyframe.mapper;

import com.feng.companyframe.bean.SysPermission;

import java.util.List;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    // 查询所有的菜单权限数据
    List<SysPermission> getAllPermissions();

    /**
     *  查询 该权限的子集
     *  将 权限id 传过来，pid 为 权限id 的所有记录，都为权限ID 的子集。看数据库便明白
     * @param permissionId
     * @return
     */
    List<SysPermission> getChild(String permissionId);

    /**
     * 根据权限id集合 获取 权限集合
     * @param permissionIds
     * @return
     */
    List<SysPermission> getPermissionByPermissionIds(List<String> permissionIds);
}