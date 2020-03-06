package com.feng.companyframe.services.impl;

import com.feng.companyframe.bean.SysPermission;
import com.feng.companyframe.constant.Constant;
import com.feng.companyframe.exception.BusinessException;
import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.jwt.JwtPropertiesConfig;
import com.feng.companyframe.mapper.SysPermissionMapper;
import com.feng.companyframe.services.PermissionService;
import com.feng.companyframe.services.RolePermissionService;
import com.feng.companyframe.services.UserRoleService;
import com.feng.companyframe.utils.RedisUtil;
import com.feng.companyframe.vo.req.PermissionAddReqVO;
import com.feng.companyframe.vo.req.PermissionUpdateReqVO;
import com.feng.companyframe.vo.resp.PermissionRespNodeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: PermissionServiceImpl
 * @Description： 描述
 * @createTime: 2020/2/11 17:39
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Resource
    private RolePermissionService rolePermissionService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private JwtPropertiesConfig jwtPropertiesConfig;

    /**
     * 获取所有的权限
     *
     * @return
     */
    @Override
    public List<SysPermission> getAllPermissions() {
        List<SysPermission> allPermissions = sysPermissionMapper.getAllPermissions();
        /**
         * 将上面的集合 遍历，对每一个权限 设置 父级权限名称
         * 因为 在SysPermission 实体类中，新增加一个属性pidName：父级名称
         */
        if (!allPermissions.isEmpty()) {
            for (SysPermission sysPermission : allPermissions) {
                SysPermission parent = sysPermissionMapper.selectByPrimaryKey(sysPermission.getPid());
                if (null != parent) {
                    // 将单独获取 权限的名称 在设置到 每一个权限的父级名称上。
                    // 也就是说在 SysPermission 实体类中，有两个属性是一样的 name 和 pidName。
                    sysPermission.setPidName(parent.getName());
                }
            }
        }
        return allPermissions;
    }

    /**
     * 获取所有的树形菜单,：目录、菜单
     *
     * @param type true :显示到 目录菜单，false：显示所有：目录菜单按钮
     * @return
     */
    @Override
    public List<PermissionRespNodeVO> getAllMenuByTree(Boolean type) {
        List<SysPermission> list = sysPermissionMapper.getAllPermissions();

        List<PermissionRespNodeVO> result = new ArrayList<>();
        PermissionRespNodeVO respNodeVO = new PermissionRespNodeVO();
        respNodeVO.setId("0");
        respNodeVO.setTitle("默认顶级菜单");
        respNodeVO.setChildren(getTree(list, type));
        result.add(respNodeVO);
        return result;
    }
    // 拉出来单独成一个方法。 递归调用

    /**
     * @param all
     * @param type true:递归到菜单， false：递归到 按钮
     * @return
     */
    private List<PermissionRespNodeVO> getTree(List<SysPermission> all, Boolean type) {
        List<PermissionRespNodeVO> list = new ArrayList<>();
        if (null == all || all.isEmpty()) {
            return list;
        } else {
            for (SysPermission sysPermission : all) {
                if ("0".equals(sysPermission.getPid())) { // 只要 顶级菜单 为 0
                    PermissionRespNodeVO permissionRespNodeVO = new PermissionRespNodeVO();
                    BeanUtils.copyProperties(sysPermission, permissionRespNodeVO);
                    permissionRespNodeVO.setTitle(sysPermission.getName());
                    if (type) {
                        //只递归到 目录和菜单，不包括按钮
                        permissionRespNodeVO.setChildren(getChildToExchangeBtn(sysPermission.getId(), all));
                    } else {
                        //递归到 目录、菜单、按钮
                        permissionRespNodeVO.setChildren(getChildAll(sysPermission.getId(), all));
                    }
                    list.add(permissionRespNodeVO);
                }
            }
        }
        return list;
    }

    /**
     * 递归调用 获取 除了 type 为 3的按钮的目录和菜单
     *
     * @param id
     * @param all
     * @return
     */
    private List<PermissionRespNodeVO> getChildToExchangeBtn(String id, List<SysPermission> all) {
        List<PermissionRespNodeVO> list = new ArrayList<>();
        for (SysPermission sysPermission : all) {
            if ((id).equals(sysPermission.getPid()) && sysPermission.getType() != 3) {
                PermissionRespNodeVO permissionRespNodeVO = new PermissionRespNodeVO();
                BeanUtils.copyProperties(sysPermission, permissionRespNodeVO);
                permissionRespNodeVO.setTitle(sysPermission.getName());
                // 开始递归
                permissionRespNodeVO.setChildren(getChildToExchangeBtn(sysPermission.getId(), all));
                list.add(permissionRespNodeVO);
            }
        }
        return list;
    }

    /**
     * 递归调用 获取 所有的 目录、菜单、按钮
     *
     * @param id
     * @param all
     * @return
     */
    private List<PermissionRespNodeVO> getChildAll(String id, List<SysPermission> all) {
        List<PermissionRespNodeVO> list = new ArrayList<>();
        for (SysPermission sysPermission : all) {
            if (sysPermission.getPid().equals(id)) {
                PermissionRespNodeVO permissionRespNode = new PermissionRespNodeVO();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());
                permissionRespNode.setChildren(getChildAll(sysPermission.getId(), all));
                list.add(permissionRespNode);
            }
        }
        return list;
    }


    /**
     * 保存 权限
     *
     * @param permissionAddReqVO
     * @return
     */
    @Override
    public SysPermission addPermission(PermissionAddReqVO permissionAddReqVO) {
        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(permissionAddReqVO, sysPermission);
        // 校验数据
        verifyForm(sysPermission);
        sysPermission.setId(UUID.randomUUID().toString());
        sysPermission.setCreateTime(new Date());

        int count = sysPermissionMapper.insertSelective(sysPermission);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return sysPermission;
    }

    /**
     * 对前端传过来的数据：目录、菜单、按钮 进行校验
     *
     * <p>
     * 操作后的菜单类型是目录的时候 父级必须为目录
     * 操作后的菜单类型是菜单的时候，父类必须为目录类型
     * 操作后的菜单类型是按钮的时候 父类必须为菜单类型
     *
     * @param sysPermission
     */
    private void verifyForm(SysPermission sysPermission) {

        SysPermission parent = sysPermissionMapper.selectByPrimaryKey(sysPermission.getPid());
        switch (sysPermission.getType()) {
            case 1: // 目录
                if (parent != null) {
                    if (parent.getType() != 1) {
                        // 父级权限不为 1 时，抛出异常：操作后的菜单类型是目录，所属菜单必须为默认顶级菜单或者目录
                        throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                    }

                    // 查不到时，默认顶级目录为 0 ，
                } else if (!sysPermission.getPid().equals("0")) {
                    // 当PID不为0时，抛出异常：操作后的菜单类型是目录，所属菜单必须为默认顶级菜单或者目录
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                }
                break;
            case 2:// 菜单
                if (parent == null || parent.getType() != 1) {
                    //操作后的菜单类型是菜单，所属菜单必须为目录类型
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_MENU_ERROR);
                }
                if (StringUtils.isEmpty(sysPermission.getUrl())) {
                    // 菜单权限的url不能为空
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }
                break;
            case 3:// 按钮
                if (parent == null || parent.getType() != 2) {
                    // 操作后的菜单类型是按钮，所属菜单必须为菜单类型
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_BTN_ERROR);
                }
                if (StringUtils.isEmpty(sysPermission.getPerms())) {
                    // 菜单权限的标识符不能为空
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_PERMS_NULL);
                }
                if (StringUtils.isEmpty(sysPermission.getUrl())) {
                    //菜单权限的url不能为空
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }
                if (StringUtils.isEmpty(sysPermission.getMethod())) {
                    //菜单权限的请求方式不能为空
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_METHOD_NULL);
                }
                break;
        }
    }

    /**
     * 根据用户获取 所有的权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<PermissionRespNodeVO> getPermissionTreeList(String userId) {
        // 对所有的 权限 整合成树，进行递归,  到菜单
        // 根据 用户id 获取权限id ，设计到三个表
        List<SysPermission> permissionsByUserId = getPermissionsByUserId(userId);
        //true： 遍历到 菜单    false： 递归到按钮
//        return getTree(getAllPermissions(), true);
        return getTree(permissionsByUserId, true);
    }


    /**
     * 获取所有的 树形菜单
     *
     * @return
     */
    @Override
    public List<PermissionRespNodeVO> getAllTree() {
        return getTree(getAllPermissions(), false);
    }

    /**
     * 更新 权限 信息
     *
     * @param vo
     */
    @Override
    public void updatePermission(PermissionUpdateReqVO vo) {

        //1.  校验数据
        SysPermission updatePermission = new SysPermission();
        BeanUtils.copyProperties(vo, updatePermission);
        // 校验父级类型
        verifyForm(updatePermission);
        SysPermission sysPermission = sysPermissionMapper.selectByPrimaryKey(vo.getId());
        if (null == sysPermission) {
            log.info("传入的 id 在数据库中不存在");
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        if (!sysPermission.getPid().equals(vo.getPid())) {
            // 所属菜单发生了变化， 要校验该权限是否存在子集
            //如果该菜单权限关联了 子集叶子 节点的话，我们就不让操作
            List<SysPermission> childSysPermissions = sysPermissionMapper.getChild(vo.getId());
            if (!childSysPermissions.isEmpty()) {
                // childSysPermissions 不为空时，报错  ： 操作的菜单权限存在 子集关联 不允许变更
                throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_UPDATE);
            }
        }
        updatePermission.setUpdateTime(new Date());
        // 2. 更新权限数据
        int i = sysPermissionMapper.updateByPrimaryKeySelective(updatePermission);
        if (1 != i) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        // 3. 判断 授权标识符 是否发生了变化,发生变化则 在redis设置 token刷新标识，进行重新刷新
        if (!sysPermission.getPerms().equals(vo.getPerms())) {
            // 不等时，发生了变化
            List<String> roleIdsByPermissionId = rolePermissionService.getRoleIdsByPermissionId(vo.getId());
            if (!roleIdsByPermissionId.isEmpty()) {
                List<String> userIdsByRoleIds = userRoleService.getUserIdsByRoleIds(roleIdsByPermissionId);
                if (!userIdsByRoleIds.isEmpty()) {
                    for (String userId : userIdsByRoleIds) {
                        redisUtil.set(Constant.JWT_REFRESH_KEY + userId, userId,
                                jwtPropertiesConfig.getAccessTokenExpireTime().toMillis(),
                                TimeUnit.MILLISECONDS);
                        /*
                        * 清除用户授权数据缓存
                        * */
                        redisUtil.delete(Constant.IDENTIFY_CACHE_KEY+userId);
                    }
                }
            }

        }
    }

    /**
     * 删除 权限  根据 权限id
     * 1、校验
     * 2、更新 权限
     * 3、判断 授权标识符
     *
     * @param permissionId
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletedPermission(String permissionId) {
        // 1.判断是否有子集关联
        List<SysPermission> child = sysPermissionMapper.getChild(permissionId);
        if (!child.isEmpty()) {
            //该菜单权限 存在 子集关联，不允许删除
            throw new BusinessException(BaseResponseCode.ROLE_PERMISSION_RELATION);
        }
        // 2.解除相关角色和该菜单权限的关联
        rolePermissionService.removeRoleByPermissionId(permissionId);

        // 3.更新权限数据
        SysPermission sysPermission = new SysPermission();
        sysPermission.setId(permissionId);
        sysPermission.setUpdateTime(new Date());
        sysPermission.setDeleted(0);
        int i = sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
        if (1 != i) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }

        /**
         * 与上面的 更新一样
         * 4.判断 授权标识符 是否发生了变化,发生变化则 在redis设置 token刷新标识，进行重新刷新
         */
        List<String> roleIdsByPermissionId = rolePermissionService.getRoleIdsByPermissionId(permissionId);
        if (!roleIdsByPermissionId.isEmpty()) {
            List<String> userIdsByRoleIds = userRoleService.getUserIdsByRoleIds(roleIdsByPermissionId);
            if (!userIdsByRoleIds.isEmpty()) {
                for (String userId : userIdsByRoleIds) {
                    redisUtil.set(Constant.JWT_REFRESH_KEY + userId, userId,
                            jwtPropertiesConfig.getAccessTokenExpireTime().toMillis(),
                            TimeUnit.MILLISECONDS);
                    /*
                     * 清除用户授权数据缓存
                     * */
                    redisUtil.delete(Constant.IDENTIFY_CACHE_KEY+userId);
                }
            }
        }

    }

    /**
     * 根据 用户id 获取权限名称 集合
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getPermissionPermsByUserId(String userId) {

        List<SysPermission> sysPermissions = getPermissionsByUserId(userId);
        Set<String> permissions = new HashSet<>();
        if (null == sysPermissions || sysPermissions.isEmpty()) {
            return null;
        }
        for (SysPermission sysPermission : sysPermissions) {
            if (!StringUtils.isEmpty(sysPermission.getPerms())) {
                permissions.add(sysPermission.getPerms());
            }
        }
        return permissions;
    }

    /**
     * 根据用户id 获取 权限集合
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysPermission> getPermissionsByUserId(String userId) {
        // 1. 从用户角色表 中获取 角色id集合
        List<String> roleIds = userRoleService.getRoleIdsByUserId(userId);
        if (roleIds.isEmpty()) {
            return null;
        }
        // 从角色权限中 根据角色id集合 获取 权限id集合
        List<String> permissionIds = rolePermissionService.getPermissionIdsByRoleIds(roleIds);

        // 从权限表中 根据权限id集合  获取权限信息集合
        List<SysPermission> result = sysPermissionMapper.getPermissionByPermissionIds(permissionIds);
        return result;
    }


}

