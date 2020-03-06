package com.feng.companyframe.services;

import com.feng.companyframe.bean.SysPermission;
import com.feng.companyframe.vo.req.PermissionAddReqVO;
import com.feng.companyframe.vo.req.PermissionUpdateReqVO;
import com.feng.companyframe.vo.resp.PermissionRespNodeVO;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    /**
     * 获取所有的权限
     * @return
     */
    List<SysPermission> getAllPermissions();

    /*
    * 获取 所有的 树形菜单
    * */
    List<PermissionRespNodeVO> getAllMenuByTree(Boolean type);

    /**
     * 保存 权限
     * @param permissionAddReqVO
     * @return
     */
    SysPermission addPermission(PermissionAddReqVO permissionAddReqVO);

    /**
     * 根据用户获取 所有的权限
     *
     * @param userId
     * @return
     */
    List<PermissionRespNodeVO> getPermissionTreeList(String userId);

    /**
     *  获取所有的 树形菜单
     * @return
     */
    List<PermissionRespNodeVO> getAllTree();

    /**
     * 更新 权限 信息
     * @param vo
     */
    void updatePermission(PermissionUpdateReqVO vo);

    /**
     * 删除 权限  根据 权限id
     * @param permissionId
     */
    void deletedPermission(String permissionId);

    /**
     * 根据 用户id 获取权限名称 集合
     * @param userId
     * @return
     */
    Set<String> getPermissionPermsByUserId(String userId);

    /**
     * 根据用户id 获取 权限集合
     * @param userId
     * @return
     */
    List<SysPermission> getPermissionsByUserId(String userId);
}
