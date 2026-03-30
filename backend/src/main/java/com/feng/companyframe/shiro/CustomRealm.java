package com.feng.companyframe.shiro;

import com.feng.companyframe.bean.SysPermission;
import com.feng.companyframe.bean.SysRole;
import com.feng.companyframe.constant.Constant;
import com.feng.companyframe.jwt.JwtTokenUtil;
import com.feng.companyframe.services.PermissionService;
import com.feng.companyframe.services.RoleService;
import com.feng.companyframe.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import javax.xml.ws.RespectBinding;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CustomRealm
 * @Description： 描述
 * @createTime: 2020/2/8 23:23
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomUsernamePasswordToken;
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 直接从 jwt 中拿即可，因为都在 claim载荷 中
        String accessToken = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Claims claims = JwtTokenUtil.getClaimsFromToken(accessToken);
        String userId = JwtTokenUtil.getUserId(accessToken);
        /**
         * 通过剩余的过期时间比较如果token的剩余过期时间大与标记key的剩余过期时间
         * 就说明这个tokne是在这个标记key之后生成的
         */
        if (redisUtil.hasKey(Constant.JWT_REFRESH_KEY + userId) && redisUtil.getExpire(Constant.JWT_REFRESH_KEY + userId, TimeUnit.MILLISECONDS) > JwtTokenUtil.getRemainingTime(accessToken)) {
            //返回该用户的 角色信息 给授权器
            List<String> roleNames = roleService.getRoleNamesByUserId(userId);
            if (null != roleNames && !roleNames.isEmpty()) {
                info.addRoles(roleNames);
            }
            //返回该用户的 权限信息 给授权器
            Set<String> permissionPerms = permissionService.getPermissionPermsByUserId(userId);
            if (permissionPerms != null) {
                info.addStringPermissions(permissionPerms);
            }
        } else {
            //返回该用户的 角色信息 给授权器
            if (null != claims.get(Constant.JWT_ROLES_KEY)) {
                Collection<String> roles = (Collection<String>) claims.get(Constant.JWT_ROLES_KEY);
                info.addRoles(roles);
            }
            //返回该用户的 权限信息 给授权器
            if (null != claims.get(Constant.JWT_PERMISSIONS_KEY)) {
                Collection<String> permissions = (Collection<String>) claims.get(Constant.JWT_PERMISSIONS_KEY);
                info.addStringPermissions(permissions);
            }
        }
        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomUsernamePasswordToken token = (CustomUsernamePasswordToken) authenticationToken;
        // 三个参数：token，password，CustomRealm字符串
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), CustomRealm.class.getName());
        return info;
    }
}

