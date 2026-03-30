package com.feng.companyframe.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @ClassName: PropertityConfig
 * @Description： token 读取的配置属性
 * @createTime: 2020/2/4 11:04
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtPropertiesConfig {
    private String secretKey;
    private Duration accessTokenExpireTime;  // java.time.Duration;
    private Duration refreshTokenExpireTime;
    private Duration refreshTokenExpireAppTime;
    private String issuer;
}

