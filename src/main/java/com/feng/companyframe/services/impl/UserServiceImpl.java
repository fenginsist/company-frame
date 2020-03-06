package com.feng.companyframe.services.impl;

import com.feng.companyframe.bean.SysDept;
import com.feng.companyframe.bean.SysRole;
import com.feng.companyframe.bean.SysUser;
import com.feng.companyframe.constant.Constant;
import com.feng.companyframe.exception.BusinessException;
import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.jwt.JwtPropertiesConfig;
import com.feng.companyframe.jwt.JwtTokenUtil;
import com.feng.companyframe.mapper.SysDeptMapper;
import com.feng.companyframe.mapper.SysUserMapper;
import com.feng.companyframe.mapper.SysUserRoleMapper;
import com.feng.companyframe.services.PermissionService;
import com.feng.companyframe.services.RoleService;
import com.feng.companyframe.services.UserRoleService;
import com.feng.companyframe.services.UserService;
import com.feng.companyframe.utils.PageUtil;
import com.feng.companyframe.utils.PasswordUtils;
import com.feng.companyframe.utils.RedisUtil;
import com.feng.companyframe.vo.req.*;
import com.feng.companyframe.vo.resp.UserOwnRoleRespVO;
import com.feng.companyframe.vo.resp.LoginRespVO;
import com.feng.companyframe.vo.resp.PageRespVO;
import com.github.pagehelper.PageHelper;
import java.lang.String;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName: UserServiceImpl
 * @Description： 描述
 * @createTime: 2020/2/4 14:55
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private JwtPropertiesConfig jwtPropertiesConfig;

    /**
     * 通过 用户名查询用户信息
     * 如果查询存在用户
     * 就比较 密码是否一致
     *
     * @param loginReqVO
     * @return
     */
    @Override
    public LoginRespVO login(LoginReqVO loginReqVO) {
        // 从数据库获取的 真实 用户信息
        SysUser userInfoByName = sysUserMapper.getUserInfoByName(loginReqVO.getUsername());
        // 判断对应出现的异常，让全局异常类 进行捕捉
        if (null == userInfoByName) { // 是否能够查出用户，如果为空，则无此用户
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }
        if (2 == userInfoByName.getStatus()) { // 用户状态如果为2 则 用户被锁住
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        // 使用 Password 工具类，带入salt 和 新、旧密码，进行判断，
        //        // 将登录密码与数据库的salt 编译成 密文密码然后与数据库密文密码进行判断
        if (!PasswordUtils.matches(userInfoByName.getSalt(), loginReqVO.getPassword(), userInfoByName.getPassword())) {
            throw new BusinessException(BaseResponseCode.ACCOUNT_PASSWORD_ERROR);
        }

        //封装返回的实体类
        LoginRespVO loginRespVO = new LoginRespVO();
        loginRespVO.setPhone(userInfoByName.getPhone());
        loginRespVO.setUsername(userInfoByName.getUsername());
        loginRespVO.setId(userInfoByName.getId());

        // 封装 map 成 claims
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constant.JWT_USER_NAME, userInfoByName.getUsername());
        // 放入 用户角色，假数据，按说是从数据库查询，已改为从数据库中 获取角色集合
        claims.put(Constant.JWT_ROLES_KEY, getRolesByUserId(userInfoByName.getId()));
        // 放入用户对应角色的权限， 假数据，按说是从数据库查询，已改为从数据库中 获取权限集合
        claims.put(Constant.JWT_PERMISSIONS_KEY, getPermissionsByUserId(userInfoByName.getId()));
        // 生成 accessToken
        String accessToken = JwtTokenUtil.getAccessToken(userInfoByName.getId(), claims);
        String refreshToken;
        // 生成 refreshToken
        if ("1".equals(loginReqVO.getType())) {
            refreshToken = JwtTokenUtil.getRefreshToken(userInfoByName.getId(), claims);
        } else {
            refreshToken = JwtTokenUtil.getRefreshAppToken(userInfoByName.getId(), claims);
        }
        loginRespVO.setAccessToken(accessToken);
        loginRespVO.setRefreshToken(refreshToken);
        return loginRespVO;
    }

    /**
     * 退出
     *
     * @param accessToken
     * @param refreshToken
     */
    @Override
    public void logout(String accessToken, String refreshToken) {
        if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(refreshToken)) {
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        Subject subject = SecurityUtils.getSubject();
        log.info("subject.getPrincipals()={}", subject.getPrincipals());

        if (subject.isAuthenticated()) {
            subject.logout();
        }
        String userId = JwtTokenUtil.getUserId(accessToken);

        /**
         * 把 token 加入黑名单 禁止再访我们的系统资源
         */
        redisUtil.set(Constant.JWT_ACCESS_TOKEN_BLACKLIST + accessToken, userId, JwtTokenUtil.getRemainingTime(accessToken), TimeUnit.MILLISECONDS);

        /**
         * 把 refreshToken 加入黑名单 禁止再拿来刷新token
         */
        redisUtil.set(Constant.JWT_REFRESH_TOKEN_BLACKLIST + refreshToken, userId, JwtTokenUtil.getRemainingTime(refreshToken), TimeUnit.MILLISECONDS);

    }

    /**
     * 根据 用户id 获取 角色名称，设计 用户角色表、角色表
     * 1. 在用户角色表， 根据用户id 获取角色id
     * 2. 在角色表， 根据角色id 获取角色信息
     * @param userId
     * @return java.util.list<java.lang.String>
     */
    private List<String> getRolesByUserId(String userId) {
        /*
         * mock 数据
         * 通过 用户id 获取该用户所拥有的角色
         * 后期修改为通过操作DB获取
        * */
        /*List<String> rolesList = new ArrayList<>();
        if ("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8".equals(userId)) {
            rolesList.add("admin");
        } else {
            rolesList.add("test");
        }
        return rolesList;*/
        List<String> roleNames = roleService.getRoleNamesByUserId(userId);
        return roleNames;
    }

    /**
     * 根据用户id 获取 所有权限信息
     * 1. 从用户角色表 根据用户id 获取角色id
     * 2. 从角色权限表 根据角色id 获取权限id
     * 3. 从权限表中   根据权限id 获取权限信息
     * @param userId
     * @return
     */
    private Set<String> getPermissionsByUserId(String userId) {
        /*
         * mock 数据
         * 通过用户id 获取该用户所拥有的角色
         * 后期修改为通过操作DB 获取
        * */
        /*List<String> permissin = new ArrayList<>();
        if ("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8".equals(userId)) { // admin 用户
            permissin.add("sys:user:list");
            permissin.add("sys:user:add");
            permissin.add("sys:user:update");
            permissin.add("sys:user:detail");
        } else {
            permissin.add("sys:user:detail");
        }
        return permissin;*/
        Set<String> permissions = permissionService.getPermissionPermsByUserId(userId);
        return permissions;
    }

    /**
     * 分页查询用户信息
     *
     * @param userPageReqVO
     * @return
     */
    @Override
    public PageRespVO<SysUser> pageInfo(UserPageReqVO userPageReqVO) {
        // 传入 默认当前页数1  默认当前页记录数10
        PageHelper.startPage(userPageReqVO.getPageNum(), userPageReqVO.getPageSize());
        // 查询所有用户
        List<SysUser> sysUsers = sysUserMapper.getAllSysUser(userPageReqVO);
        for (SysUser sysUser : sysUsers) {
            SysDept sysDept = sysDeptMapper.selectByPrimaryKey(sysUser.getDeptId());
            if (null != sysDept) {
                sysUser.setDeptName(sysDept.getName());
            }
        }
        return PageUtil.getPageVO(sysUsers);
    }

    /**
     * 添加新用户
     *
     * @param vo
     */
    @Override
    public void addUser(UserAddReqVO vo) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(vo, sysUser);
        // 用户设置salt
        sysUser.setSalt(PasswordUtils.getSalt());
        String password = PasswordUtils.encode(vo.getPassword(), sysUser.getSalt());
        sysUser.setPassword(password);
        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setCreateTime(new Date());

        int count = sysUserMapper.insertSelective(sysUser);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    /**
     * 根据用户id获取用户拥有角色
     *
     * @param userId
     * @return
     */
    @Override
    public UserOwnRoleRespVO getUserOwnRole(String userId) {
        List<String> roleIdsByUserId = sysUserRoleMapper.getRoleIdsByUserId(userId);
        List<SysRole> allRoles = roleService.getAllRoles();

        UserOwnRoleRespVO vo = new UserOwnRoleRespVO();
        vo.setAllRole(allRoles);
        vo.setOwnRoles(roleIdsByUserId);
        return vo;
    }

    /**
     * 添加用户 权限
     *
     * @param vo
     */
    @Override
    public void setUserOwnRole(UserOwnRoleReqVO vo) {
        // 调用 用户角色 业务层，进行删除和新增 用户角色信息
        userRoleService.addUserRoleInfoAndRemoveUserInfo(vo);
        /*
         * 标记用户 要主动去刷新
         * */
        redisUtil.set(Constant.JWT_REFRESH_KEY + vo.getUserId(), vo.getUserId(),
                jwtPropertiesConfig.getAccessTokenExpireTime().toMillis(),
                TimeUnit.MILLISECONDS);
        /*
         * 清除用户授权数据缓存
         * */
        redisUtil.delete(Constant.IDENTIFY_CACHE_KEY+vo.getUserId());
    }

    /**
     * 更新用户信息
     * @param vo
     * @param operationId 操作的人员
     */
    @Override
    public void updateUserInfo(UserUpdateReqVO vo, String operationId) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(vo, sysUser);
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateId(operationId);
        if (StringUtils.isEmpty(vo.getPassword())){
            sysUser.setPassword(null);
        }else{
            String salt = PasswordUtils.getSalt();
            String endPwd = PasswordUtils.encode(vo.getPassword(), salt);
            sysUser.setSalt(salt);
            sysUser.setPassword(endPwd);
        }

        int i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (1!=i){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        //  判断 用户是否为锁定
        if(2==vo.getStatus()){
            redisUtil.set(Constant.ACCOUNT_LOCK_KEY+vo.getId(), vo.getId());
        }else {
            redisUtil.delete(Constant.ACCOUNT_LOCK_KEY+vo.getId());
        }
    }

    /**
     * 删除用户，单个删除和批量删除
     * @param userIdList
     * @param operationId 操作人 id
     */
    @Override
    public void deletedUsers(List<String> userIdList, String operationId) {
        SysUser sysUser = new SysUser();
        sysUser.setUpdateId(operationId);
        sysUser.setUpdateTime(new Date());
        sysUser.setDeleted(0);
        int i = sysUserMapper.deletedUsers(sysUser, userIdList);
        if (i==0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        // 设置 刷新标记
        for (String userId : userIdList){
            redisUtil.set(Constant.DELETED_USER_KEY+userId, userId,
                    jwtPropertiesConfig.getRefreshTokenExpireAppTime().toMillis(),
                    TimeUnit.MILLISECONDS);
            /*
             * 清除用户授权数据缓存
             * */
            redisUtil.delete(Constant.IDENTIFY_CACHE_KEY+userId);
        }
    }

    /**
     * 自动刷新 token， 使用 refreshToken， 来刷新 AccessToken
     *
     * @param refreshTOken
     * @return
     */
    @Override
    public String refreshToken(String refreshTOken) {
        // 它是否过期， 它是否被加入了黑名单
        if (!JwtTokenUtil.validateToken(refreshTOken) || redisUtil.hasKey(Constant.JWT_REFRESH_TOKEN_BLACKLIST)) {
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        String userId = JwtTokenUtil.getUserId(refreshTOken);
        log.info("userId={}", userId);
        Map<String, Object> claims = null;
        /*
         * 用户主动去刷新
         * 更新角色/权限信息
         * */
        if (redisUtil.hasKey(Constant.JWT_REFRESH_KEY + userId)) {
            claims = new HashMap<>();
            claims.put(Constant.JWT_ROLES_KEY, getRolesByUserId(userId));
            claims.put(Constant.JWT_PERMISSIONS_KEY, getPermissionsByUserId(userId));
        }
        String newAccessToken = JwtTokenUtil.refreshToken(refreshTOken, claims);
        /*
         * 如果是主动去刷新, 则 redis 标记新的 access_token
         * 过期时间为 key = Constant.JWT_REFRESH_KEY+userId 的剩余过期时间
         * */
//        if (redisUtil.hasKey(Constant.JWT_REFRESH_KEY + userId)) {
//            redisUtil.set(Constant.JWT_REFRESH_IDENTIFICATION + newAccessToken, userId,
//                    redisUtil.getExpire(Constant.JWT_REFRESH_KEY + userId, TimeUnit.MILLISECONDS),
//                    TimeUnit.MILLISECONDS);
//        }

        return newAccessToken;
    }

    /**
     * 根据部门id集合 获取 用户信息
     * @param deptIds
     * @return
     */
    @Override
    public List<SysUser> getUserInfoByDeptIds(List<String> deptIds) {
        return sysUserMapper.getUserInfoByDeptIds(deptIds);
    }

    /**
     * 根据登录用户id 获取登录用户详细信息
     * @param userId
     * @return
     */
    @Override
    public SysUser getLoginUserDetailInfo(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    /**
     * 个人用户编辑信息接口
     * @param vo
     */
    @Override
    public void updateLoginUserDetailInfo(UserLoginUpdateDetailInfoReqVO vo, String userId) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(vo, sysUser);
        sysUser.setId(userId);
        sysUser.setUpdateId(userId);
        sysUser.setUpdateTime(new Date());

        int count = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (1!=count){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    /**
     * 更新个人密码
     *
     * @param vo
     * @param accessToken
     * @param refreshToken
     */
    @Override
    public void updateLoginUserPassword(UserUpdatePWDReqVO vo, String accessToken, String refreshToken) {
        String userId = JwtTokenUtil.getUserId(accessToken);
        // 校验旧密码
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (null == sysUser){
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        if (!PasswordUtils.matches(sysUser.getSalt(), vo.getOldPwd(), sysUser.getPassword())){
            throw new BusinessException(BaseResponseCode.OLD_PASSWORD_ERROR);
        }
        // 保存 新密码
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateId(userId);
        sysUser.setPassword(PasswordUtils.encode(vo.getNewPwd(), sysUser.getSalt()));
        int count = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (1 != count){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }

        /**
         * 把 token 加入黑名单 禁止再访我们的系统资源
         */
        redisUtil.set(Constant.JWT_ACCESS_TOKEN_BLACKLIST + accessToken, userId, JwtTokenUtil.getRemainingTime(accessToken), TimeUnit.MILLISECONDS);
        /**
         * 把 refreshToken 加入黑名单 禁止再拿来刷新token
         */
        redisUtil.set(Constant.JWT_REFRESH_TOKEN_BLACKLIST + refreshToken, userId, JwtTokenUtil.getRemainingTime(refreshToken), TimeUnit.MILLISECONDS);

        /*
         * 清除用户授权数据缓存
         * */
        redisUtil.delete(Constant.IDENTIFY_CACHE_KEY+userId);
    }




}

