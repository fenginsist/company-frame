package com.feng.companyframe.shiro;

import com.feng.companyframe.constant.Constant;
import com.feng.companyframe.exception.BusinessException;
import com.feng.companyframe.exception.code.BaseResponseCode;
import com.feng.companyframe.jwt.JwtTokenUtil;
import com.feng.companyframe.utils.RedisUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CustomHashedCredentialsMatcher
 * @Description： token 认证的 过滤
 * @createTime: 2020/2/5 19:21
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private RedisUtil redisUtil;


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
        if (redisUtil.hasKey(Constant.ACCOUNT_LOCK_KEY + userId)) {
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        /**
         * 第二步：判断用户是否被删除。
         * 否：下一步。
         * 是：引导到登录界面。
         */
        if (redisUtil.hasKey(Constant.DELETED_USER_KEY + userId)) {
            throw new BusinessException(BaseResponseCode.ACCOUNT_HAS_DELETED_ERROR);
        }
        /**
         * 第三步：判断用户是否是否主动退出(用户主动退出后端会把 Contants.JWT_ACCESS_TOKEN_BLACKLIST+access_token 作为 key 存入
         * redis 并且设置过期时间为 access_token 剩余的过期时间)
         * 否：下一步。
         * 是：引导到登录界面。
         */
        if (redisUtil.hasKey(Constant.JWT_ACCESS_TOKEN_BLACKLIST + accessToken)) {
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
         * (因为后台修改了用户所拥有的角色/菜单权限的时候  需要把相关联用户都用 redis 标记起来(过期时间为
         * access_token 生成的过期时间，需要刷新 access_token 重新分配角色)但是呢 需要排除重新登录的用户 所以呢还要比较这个
         * accessToken 和 Constant.JWT_REFRESH_KEY+userId 两者的剩余过期时间。
         * 满足条件：下一步。
         * 不满足：放行 处理相关业务。
         *
         * 如果 key = Constant.JWT_REFRESH_KEY+userId 大于 accessToken 说明是在 accessToken 不是重新生成的
         * 这样就要判断它是否刷新过了/或者是否是新生成的 token
         **/
        /*
        * 在赋予用户 角色的时候 会 set 这个值：Constant.JWT_REFRESH_KEY + userId， 约定好
        * */
//        if (redisUtil.hasKey(Constant.JWT_REFRESH_KEY + userId)
//                && redisUtil.getExpire(Constant.JWT_REFRESH_KEY + userId, TimeUnit.MILLISECONDS)
//                > JwtTokenUtil.getRemainingTime(accessToken)) {
//            /**
//             * 是否存在刷新的标识
//             * 这个标志 会在 刷新token进行set，refreshToken()方法中。
//             */
//            if (!redisUtil.hasKey(Constant.JWT_REFRESH_IDENTIFICATION + accessToken)) {
//                //  返回到前端，进行主动刷新。
//                throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
//            }
//        }
        if (redisUtil.hasKey(Constant.JWT_REFRESH_KEY + userId)) {
            /**
             * 通过剩余的过期时间比较如果token的剩余过期时间 大于 标记key的剩余过期时间
             * 就说明这个 token 是在这个标记 key 之后生成的
             */
            if (redisUtil.getExpire(Constant.JWT_REFRESH_KEY + userId, TimeUnit.MILLISECONDS)
                    > JwtTokenUtil.getRemainingTime(accessToken)) {
                //  返回到前端，进行主动刷新。
                throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
            }
        }

        return true;
    }
}