package com.feng.companyframe.mapper;

import com.feng.companyframe.bean.SysRole;
import com.feng.companyframe.vo.req.RolePageReqVO;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /*
    * 根据条件获取 所有的 角色
    * */
    List<SysRole> getAllRole(RolePageReqVO rolePageReqVO);

    /**
     * 根据角色id集合 获取 角色信息
     * @param roleIds
     * @return
     */
    List<SysRole> getRoleInfoByRoleIds(List<String> roleIds);
}