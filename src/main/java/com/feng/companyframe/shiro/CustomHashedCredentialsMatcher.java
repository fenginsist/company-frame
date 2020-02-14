package com.feng.companyframe.shiro;

import com.feng.companyframe.constant.Constants;
import com.feng.companyframe.exception.BusinessException;
import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.jwt.JwtTokenUtil;
import com.feng.companyframe.utils.RedisUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CustomHashedCredentialsMatcher
 * @Description： 描述
 * @createTime: 2020/2/5 19:21
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomUsernamePasswordToken customUsernamePasswordToken = (CustomUsernamePasswordToken) token;
        String accessToken = (String) customUsernamePasswordToken.getPrincipal(); // 获取 token
        String userId = JwtTokenUtil.getUserId(accessToken); // 获取 用户 id
        /**
         * 第一步：判断用户是否被锁定。
         * 否：下一步。
         * 是：引导到登录界面。
         */
        if (redisUtils.hasKey(Constants.ACCOUNT_LOCK_KEY + userId)) {
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        /**
         * 第二步：判断用户是否被删除。
         * 否：下一步。
         * 是：引导到登录界面。
         */
        if (redisUtils.hasKey(Constants.DELETED_USER_KEY + userId)) {
            throw new BusinessException(BaseResponseCode.ACCOUNT_HAS_DELETED_ERROR);
        }
        /**
         * 第三步：判断用户是否是否主动退出(用户主动退出后端会把 Contants.JWT_ACCESS_TOKEN_BLACKLIST+access_token 作为 key 存入
         * redis 并且设置过期时间为 access_token 剩余的过期时间)
         * 否：下一步。
         * 是：引导到登录界面。
         */
        if (redisUtils.hasKey(Constants.JWT_ACCESS_TOKEN_BLACKLIST + accessToken)) {
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        /**
         * 第四步：判断 access_token 是否通过校验(校验是否过期)
         * 否（未过期）：下一步。
         * 是（已过期）：引导到登录界面。
         */
        if (!JwtTokenUtil.validateToken(accessToken)) {
            throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
        }


// 这里不太明白

        /**
         * 第五步：判断这个登录用户是否要主动去刷新
         * (因为后台修改了用户所拥有的角色/菜单权限的时候  需要把相关联用户都用redis标记起来(过期时间为
         * access_token 生成的过期时间)，需要刷新 access_token 重新分配角色)但是呢 需要排除重新登录的用户 所以呢还要比较这个
         * accessToken 和 Constant.JWT_REFRESH_KEY+userId 两者的剩余过期时间。
         * 满足条件：下一步。
         * 不满足：放行 处理相关业务。
         *
         * 如果 key=Constant.JWT_REFRESH_KEY+userId 大于 accessToken 说明是在 accessToken 不是重新生成的
         * 这样就要判断它是否刷新过了/或者是否是新生成的token
         **/
        if (redisUtils.hasKey(Constants.JWT_REFRESH_KEY + userId)
                && redisUtils.getExpire(Constants.JWT_REFRESH_KEY + userId, TimeUnit.MILLISECONDS)
                > JwtTokenUtil.getRemainingTime(accessToken)) {
            /**
             * 是否存在刷新的标识
             */
            if (!redisUtils.hasKey(Constants.JWT_REFRESH_IDENTIFICATION + accessToken)) {
                throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
            }
        }
        return true;
    }
}