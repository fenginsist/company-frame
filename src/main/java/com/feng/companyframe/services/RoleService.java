package com.feng.companyframe.services;

import com.feng.companyframe.bean.SysRole;
import com.feng.companyframe.vo.req.RoleAddReqVO;
import com.feng.companyframe.vo.req.RolePageReqVO;
import com.feng.companyframe.vo.req.RoleUpdateReqVO;
import com.feng.companyframe.vo.resp.PageRespVO;

import java.util.List;

public interface RoleService {

    /**
     * 根据条件  获取 角色信息,采用 分页
     * @param rolePageReqVO
     * @return
     */
    PageRespVO<SysRole> pageInfo(RolePageReqVO rolePageReqVO);

    /**
     * 添加 角色 role
     * @param roleAddReqVO
     * @return
     */
    SysRole addRole(RoleAddReqVO roleAddReqVO);

    /**
     * 获取所有角色接口
     * @return
     */
    List<SysRole> getAllRoles();

    /**
     * 通过 角色id 获取详细信息
     * @param roleId
     * @return
     */
    SysRole getDetailInfoByRoleId(String roleId);

    /**
     * 更新 角色信息
     * @param vo
     */
    void updateRole(RoleUpdateReqVO vo);

    /**
     * 根据角色id 删除该角色
     * @param roleId
     */
    void deletedRoleByRoleId(String roleId);

    /**
     * 根据 用户id 获取 角色名称集合
     * @param userId
     * @return
     */
    List<String> getRoleNamesByUserId(String userId);

    /**
     * 根据用户id 获取 角色信息集合
     * @param userId
     * @return
     */
    List<SysRole> getRoleInfoByUserId(String userId);

}
