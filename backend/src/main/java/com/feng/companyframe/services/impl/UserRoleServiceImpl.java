package com.feng.companyframe.services.impl;

import com.feng.companyframe.bean.SysUserRole;
import com.feng.companyframe.exception.BusinessException;
import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.mapper.SysUserRoleMapper;
import com.feng.companyframe.services.UserRoleService;
import com.feng.companyframe.vo.req.UserOwnRoleReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: UserRoleServiceImpl
 * @Description： 描述
 * @createTime: 2020/2/19 22:50
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 根据用户id 获取 角色Id
     * @param userId
     * @return
     */
    @Override
    public List<String> getRoleIdsByUserId(String userId) {
        List<String> roleIdsByUserId = sysUserRoleMapper.getRoleIdsByUserId(userId);
        return roleIdsByUserId;
    }

    /**
     * 添加 用户角色 信息
     * @param vo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUserRoleInfoAndRemoveUserInfo(UserOwnRoleReqVO vo) {
        // 删除用户id 对应的角色
        sysUserRoleMapper.removeByUserId(vo.getUserId());

        if (null == vo.getRoleIds() || vo.getRoleIds().isEmpty()){
            return;
        }

        List<SysUserRole> list = new ArrayList<>();
        for (String roleId: vo.getRoleIds()){
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setId(UUID.randomUUID().toString());
            sysUserRole.setCreateTime(new Date());
            sysUserRole.setUserId(vo.getUserId());
            sysUserRole.setRoleId(roleId);
            list.add(sysUserRole);
        }
        int count = sysUserRoleMapper.batchUserRole(list);
        if (0 == count){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    /**
     * 用户 用户id集合 根据 角色id集合，在用户角色表中
     * @param roleIds
     * @return
     */
    @Override
    public List<String> getUserIdsByRoleIds(List<String> roleIds) {
        return sysUserRoleMapper.getUserIdsByRoleIds(roleIds);
    }

    /**
     * 根据角色id 获取 用户id集合
     * @param roleId
     * @return
     */
    @Override
    public List<String> getUserIdsByRoleId(String roleId) {
        return sysUserRoleMapper.getUserIdsByRoleId(roleId);
    }
    /**
     * 根据角色id 删除该角色关联菜单权限所有数据
     * @param roleId
     * @return
     */
    public int removeByRoleId(String roleId){
        return sysUserRoleMapper.removeByRoleId(roleId);
    }
}

