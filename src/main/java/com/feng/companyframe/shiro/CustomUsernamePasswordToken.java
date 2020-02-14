package com.feng.companyframe.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @ClassName: CustomPasswordToken
 * @Description： 描述
 * @createTime: 2020/2/5 19:11
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class CustomUsernamePasswordToken extends UsernamePasswordToken {
    private String token;

    public CustomUsernamePasswordToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}

