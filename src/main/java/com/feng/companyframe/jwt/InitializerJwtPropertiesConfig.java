package com.feng.companyframe.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: InitializerJwtPropertiesConfig
 * @Description： 将yml配置文件中的数据注入到 JwtTokenUtil 工具类中
 * @createTime: 2020/2/5 10:42
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Configuration
public class InitializerJwtPropertiesConfig {

//    @Autowired
    private JwtPropertiesConfig jwtPropertiesConfig;

    public InitializerJwtPropertiesConfig(JwtPropertiesConfig jwtPropertiesConfig) {
        JwtTokenUtil.setTokenProperties(jwtPropertiesConfig);
    }
}

