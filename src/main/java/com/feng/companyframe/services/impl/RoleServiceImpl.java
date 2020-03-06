package com.feng.companyframe.services.impl;

import com.feng.companyframe.bean.SysRole;
import com.feng.companyframe.constant.Constant;
import com.feng.companyframe.exception.BusinessException;
import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.jwt.JwtPropertiesConfig;
import com.feng.companyframe.mapper.SysRoleMapper;
import com.feng.companyframe.services.*;
import com.feng.companyframe.utils.PageUtil;
import com.feng.companyframe.utils.RedisUtil;
import com.feng.companyframe.vo.req.RoleAddReqVO;
import com.feng.companyframe.vo.req.RolePageReqVO;
import com.feng.companyframe.vo.req.RolePermissionOperationReqVO;
import com.feng.companyframe.vo.req.RoleUpdateReqVO;
import com.feng.companyframe.vo.resp.PageRespVO;
import com.feng.companyframe.vo.resp.PermissionRespNodeVO;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName: RoleServiceImpl
 * @Description： 描述
 * @createTime: 2020/2/17 18:33
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private RolePermissionService rolePermissionService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private JwtPropertiesConfig jwtPropertiesConfig;

    /**
     * 根据条件  获取 角色信息,采用 分页
     * @param rolePageReqVO
     * @return
     */
    @Override
    public PageRespVO<SysRole> pageInfo(RolePageReqVO rolePageReqVO) {
        PageHelper.offsetPage(rolePageReqVO.getPageNum(), rolePageReqVO.getPageSize());
        List<SysRole> allRole = sysRoleMapper.getAllRole(rolePageReqVO);
        //  使用分页工具类 包装成  PageRespVO 类对象
        return PageUtil.getPageVO(allRole);
    }

    /**
     * 添加 角色 role
     * @param roleAddReqVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysRole addRole(RoleAddReqVO roleAddReqVO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleAddReqVO, sysRole);
        sysRole.setId(UUID.randomUUID().toString());
        sysRole.setCreateTime(new Date());
        // 插入角色
        int count = sysRoleMapper.insertSelective(sysRole);
        if (1!=count){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        if (roleAddReqVO.getPermissionIds()!=null && !roleAddReqVO.getPermissionIds().isEmpty()){
            RolePermissionOperationReqVO operationReqVO = new RolePermissionOperationReqVO();
            operationReqVO.setRoleId(sysRole.getId());
            operationReqVO.setPermissionIds(roleAddReqVO.getPermissionIds());
            // 插入 角色-权限 对象，在里面是批量插入
            rolePermissionService.addRolePermission(operationReqVO);
        }
        return sysRole;
    }


    /**
     * 获取所有角色接口
     * @return
     */
    @Override
    public List<SysRole> getAllRoles() {
        List<SysRole> allRole = sysRoleMapper.getAllRole(new RolePageReqVO());
        return allRole;
    }

    /**
     * 通过 角色id 获取详细信息
     * @param roleId
     * @return
     */
    @Override
    public SysRole getDetailInfoByRoleId(String roleId) {
        // 1. 校验数据
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
        if (null == sysRole){
            log.info("传入的 id：{}不合法", roleId);
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        // 2. 获取所有 菜单权限树 目录、菜单
        List<PermissionRespNodeVO> allTree = permissionService.getAllTree();

        // 3. 获取该角色拥有的菜单权限id集合: 在 角色权限表中，根据角色id 获取 所有的权限id
        List<String> permissionIdsByRoleId = rolePermissionService.getPermissionIdsByRoleId(roleId);
        Set<String> permissionIds = new HashSet<>(permissionIdsByRoleId);
        // 4. 遍历 菜单权限树 的数据
        setChecked(allTree, permissionIds);
        // 5. 将权限树 放到 实体类中
        sysRole.setPermissionRespNodeVOS(allTree);
        return sysRole;
    }
    private void setChecked(List<PermissionRespNodeVO> list, Set<String> permissionIds){
        for (PermissionRespNodeVO node : list){
            /*
            * 子集选中从它往上到根目录 都被选中，父级选中从它到它所有的叶子节点都会被选中
            * 这样我们就直接遍历最底层级 就可以了
            * */
            if (permissionIds.contains(node.getId()) && (null == node.getChildren() || node.getChildren().isEmpty())){
                node.setChecked(true);
            }
            // 递归
            setChecked((List<PermissionRespNodeVO>) node.getChildren(), permissionIds);
        }
    }

    /**
     * 更新 角色信息
     * @param vo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRole(RoleUpdateReqVO vo) {
        //数据校验
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(vo.getId());
        if (null == sysRole){
            log.info("传入的 id：{}不合法", vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        //1. 更新角色 信息
        BeanUtils.copyProperties(vo, sysRole);
        int i = sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        if (1!=i){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }

        //2. 修改  角色和菜单权限 关联数据
        RolePermissionOperationReqVO rolePermissionOperationReqVO = new RolePermissionOperationReqVO();
        rolePermissionOperationReqVO.setRoleId(vo.getId());
        rolePermissionOperationReqVO.setPermissionIds(vo.getPermissionIds());
        rolePermissionService.addRolePermission(rolePermissionOperationReqVO);

        // 3.标记关联用户,让其主动刷新
        List<String> userIdsByRoleId = userRoleService.getUserIdsByRoleId(vo.getId());
        if (!userIdsByRoleId.isEmpty()){
            for (String userId : userIdsByRoleId){
                /*
                * 标记用户 在用户认证的时候 判断这个是否主动刷过
                * */
                redisUtil.set(Constant.JWT_REFRESH_KEY+userId, userId,
                        jwtPropertiesConfig.getAccessTokenExpireTime().toMillis(),
                        TimeUnit.MILLISECONDS);
                /*
                 * 清除用户授权数据缓存
                 * */
                redisUtil.delete(Constant.IDENTIFY_CACHE_KEY+userId);
            }
        }
    }

    /**
     * 根据角色id 删除该角色
     * @param roleId
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletedRoleByRoleId(String roleId) {
        SysRole sysRole = new SysRole();
        sysRole.setId(roleId);
        sysRole.setDeleted(0);
        sysRole.setUpdateTime(new Date());
        // 1. 更新数据
        int i = sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        if (1!=i){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        // 2. 删除 角色、菜单权限关联数据
        rolePermissionService.removeRolePermissionByRoleId(roleId);
        List<String> userIdsByRoleId = userRoleService.getUserIdsByRoleId(roleId);
        // 3. 删除 用户、角色 关联数据
        userRoleService.removeByRoleId(roleId);

        // 4. 把该角色关联的用户标记起来，需要刷新token
        if (!userIdsByRoleId.isEmpty()){
            for (String userId : userIdsByRoleId){
                /*
                 * 标记用户 在用户认证的时候 判断这个是否主动刷过
                 * */
                redisUtil.set(Constant.JWT_REFRESH_KEY+userId, userId,
                        jwtPropertiesConfig.getAccessTokenExpireTime().toMillis(),
                        TimeUnit.MILLISECONDS);
                /*
                 * 清除用户授权数据缓存
                 * */
                redisUtil.delete(Constant.IDENTIFY_CACHE_KEY+userId);
            }
        }
    }

    /**
     * 根据 用户id 获取 角色名称集合
     * @param userId
     * @return
     */
    @Override
    public List<String> getRoleNamesByUserId(String userId) {
        // 通过 用户id 获取 角色信息：：：1. 先从用户角色表获取角色id，2.在从角色表中获取角色信息
        List<SysRole> sysRoles = getRoleInfoByUserId(userId);
        if (null == sysRoles || sysRoles.isEmpty()){
            return null;
        }
        List<String> list = new ArrayList<>();
        for (SysRole sysRole : sysRoles){
            list.add(sysRole.getName());
        }
        return list;
    }

    /**
     * 根据用户id 获取 角色信息集合
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> getRoleInfoByUserId(String userId) {
        // 1. 从用户角色表中 获取 角色id
        List<String> roleIds = userRoleService.getRoleIdsByUserId(userId);
        if (roleIds.isEmpty()){
            return null;
        }
        // 2. 从角色表中 获取 角色信息
        return sysRoleMapper.getRoleInfoByRoleIds(roleIds);
    }
}

