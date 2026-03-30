package com.feng.companyframe.services;

import com.feng.companyframe.vo.req.RolePermissionOperationReqVO;

import java.util.List;

public interface RolePermissionService {

    /**
     * 批量添加 角色-权限 到 角色权限表中
     * @param vo
     */
    void addRolePermission(RolePermissionOperationReqVO vo);

    /**
     *根据菜单权限id获取关联的角色id集合
     * @param permissionId
     * @return
     */
    List<String> getRoleIdsByPermissionId(String permissionId);

    /**
     * 删除关联表的 角色 通过权限id
     * @param permissionId
     * @return
     */
    int removeRoleByPermissionId(String permissionId);

    /**
     * 根据角色id获取该角色关联的菜单权限id集合
     * @param roleId
     * @return
     */
    List<String> getPermissionIdsByRoleId(String roleId);

    /**
     * 根据角色id 删除 角色、权限关联表数据
     * @param roleId
     * @return
     */
    int removeRolePermissionByRoleId(String roleId);


    /**
     * 根据角色id集合  获取权限id集合
     * @param roleIds
     * @return
     */
    List<String> getPermissionIdsByRoleIds(List<String> roleIds);

}
