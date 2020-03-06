package com.feng.companyframe.services.impl;

import com.feng.companyframe.bean.SysPermission;
import com.feng.companyframe.bean.SysRolePermission;
import com.feng.companyframe.exception.BusinessException;
import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.mapper.SysRolePermissionMapper;
import com.feng.companyframe.services.RolePermissionService;
import com.feng.companyframe.vo.req.RolePermissionOperationReqVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: RolePermissionService
 * @Description： 描述
 * @createTime: 2020/2/18 14:24
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Resource
    private RolePermissionService rolePermissionService;

    /**
     * 批量添加 角色-权限 到 角色权限表中
     * @param vo
     */
    @Override
    public void addRolePermission(RolePermissionOperationReqVO vo) {

        // 在角色权限表中 删除 原有的 角色权限信息
        rolePermissionService.removeRolePermissionByRoleId(vo.getRoleId());
        //这里要把 && 给成||
        if (vo.getPermissionIds() == null || vo.getPermissionIds().isEmpty()){
            return;
        }
        List<SysRolePermission> list = new ArrayList<>();
        // 用户 id 固定， 添加每一个 权限ID
        for (String permissionId : vo.getPermissionIds()){
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setId(UUID.randomUUID().toString());
            sysRolePermission.setRoleId(vo.getRoleId());
            sysRolePermission.setPermissionId(permissionId);
            sysRolePermission.setCreateTime(new Date());
            list.add(sysRolePermission);
        }
        // 批量插入操作
        int count = sysRolePermissionMapper.batchInsertRolePermission(list);
        if (count == 0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    /**
     *根据菜单权限id 获取 关联的角色id集合
     * @param permissionId
     * @return
     */
    @Override
    public List<String> getRoleIdsByPermissionId(String permissionId) {
        return sysRolePermissionMapper.getRoleIdsByPermissionId(permissionId);
    }

    /**
     * 删除关联表的 角色 通过权限id
     * @param permissionId
     * @return
     */
    @Override
    public int removeRoleByPermissionId(String permissionId) {
        return sysRolePermissionMapper.removeByPermissionId(permissionId);
    }

    /**
     * 根据角色id获取该角色关联的菜单权限id集合
     * @param roleId
     * @return
     */
    @Override
    public List<String> getPermissionIdsByRoleId(String roleId) {
        return sysRolePermissionMapper.getPermissionIdsByRoleId(roleId);
    }

    @Override
    public int removeRolePermissionByRoleId(String roleId) {
        return sysRolePermissionMapper.removeByRoleId(roleId);
    }

    /**
     * 根据角色id集合  获取权限id集合
     * @param roleIds
     * @return
     */
    @Override
    public List<String> getPermissionIdsByRoleIds(List<String> roleIds) {
        List<String> permissionIds = sysRolePermissionMapper.getPermissionIdsByRoleIds(roleIds);
        return permissionIds;
    }
}

