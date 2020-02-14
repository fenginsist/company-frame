package com.feng.companyframe.services.impl;

import com.feng.companyframe.bean.SysUser;
import com.feng.companyframe.constant.Constants;
import com.feng.companyframe.exception.BusinessException;
import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.jwt.JwtTokenUtil;
import com.feng.companyframe.mapper.SysUserMapper;
import com.feng.companyframe.services.UserService;
import com.feng.companyframe.utils.PageUtil;
import com.feng.companyframe.utils.PasswordUtils;
import com.feng.companyframe.utils.RedisUtils;
import com.feng.companyframe.vo.req.LoginReqVO;
import com.feng.companyframe.vo.req.UserPageReqVO;
import com.feng.companyframe.vo.resp.LoginRespVO;
import com.feng.companyframe.vo.resp.PageRespVO;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private RedisUtils redisUtils;

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
        if (null == userInfoByName) {
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }
        if (2 == userInfoByName.getStatus()) {
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        //  这里有点不明白
        // 使用 Password 工具类，带入salt 和 新、旧密码，进行判断
        if (PasswordUtils.matches(userInfoByName.getSalt(), loginReqVO.getPassword(), userInfoByName.getPassword())) {
            throw new BusinessException(BaseResponseCode.ACCOUNT_PASSWORD_ERROR);
        }

        //封装返回的实体类
        LoginRespVO loginRespVO = new LoginRespVO();
        loginRespVO.setPhone(userInfoByName.getPhone());
        loginRespVO.setUsername(userInfoByName.getUsername());
        loginRespVO.setId(userInfoByName.getId());

        // 封装 map 成 claims
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.JWT_USER_NAME, userInfoByName.getUsername());
        claims.put(Constants.JWT_ROLES_KEY, getRolesByUserId(userInfoByName.getId()));              // 假数据，按说是从数据库查询
        claims.put(Constants.JWT_PERMISSIONS_KEY, getPermissionsByUserId(userInfoByName.getId())); // 假数据，按说是从数据库查询
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
         * 把token 加入黑名单 禁止再登录
         */
        redisUtils.set(Constants.JWT_ACCESS_TOKEN_BLACKLIST + accessToken, userId, JwtTokenUtil.getRemainingTime(accessToken), TimeUnit.MILLISECONDS);
        /**
         * 把 refreshToken 加入黑名单 禁止再拿来刷新token
         */
        redisUtils.set(Constants.JWT_REFRESH_TOKEN_BLACKLIST + refreshToken, userId, JwtTokenUtil.getRemainingTime(refreshToken), TimeUnit.MILLISECONDS);

    }

    /**
     * mock 数据
     * 通过 用户id 获取该用户所拥有的角色
     * 后期修改为通过操作DB获取
     *
     * @param userId
     * @return java.util.list<java.lang.String>
     */
    private List<String> getRolesByUserId(String userId) {
        List<String> rolesList = new ArrayList<>();
        if ("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8".equals(userId)) {
            rolesList.add("admin");
        } else {
            rolesList.add("test");
        }
        return rolesList;
    }

    /**
     * mock 数据
     * 通过用户id 获取该用户所拥有的角色
     * 后期修改为通过操作DB 获取
     *
     * @param userId
     * @return
     */
    private List<String> getPermissionsByUserId(String userId) {
        List<String> permissin = new ArrayList<>();
        if ("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8".equals(userId)) { // admin 用户
            permissin.add("sys:user:list");
            permissin.add("sys:user:add");
            permissin.add("sys:user:update");
            permissin.add("sys:user:detail");
        }else{
            permissin.add("sys:user:detail");
        }
        return permissin;
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
        return PageUtil.getPageVO(sysUsers);
    }
}

