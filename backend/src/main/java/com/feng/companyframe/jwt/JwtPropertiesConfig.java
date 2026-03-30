package com.feng.companyframe.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Objects;

/**
 * @ClassName: PropertityConfig
 * @Description： token 读取的配置属性
 * @createTime: 2020/2/4 11:04
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtPropertiesConfig {
    private String secretKey;
    private Duration accessTokenExpireTime;  // java.time.Duration;
    private Duration refreshTokenExpireTime;
    private Duration refreshTokenExpireAppTime;
    private String issuer;

    public JwtPropertiesConfig() {
    }

    public JwtPropertiesConfig(String secretKey, Duration accessTokenExpireTime, Duration refreshTokenExpireTime, Duration refreshTokenExpireAppTime, String issuer) {
        this.secretKey = secretKey;
        this.accessTokenExpireTime = accessTokenExpireTime;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
        this.refreshTokenExpireAppTime = refreshTokenExpireAppTime;
        this.issuer = issuer;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Duration getAccessTokenExpireTime() {
        return accessTokenExpireTime;
    }

    public void setAccessTokenExpireTime(Duration accessTokenExpireTime) {
        this.accessTokenExpireTime = accessTokenExpireTime;
    }

    public Duration getRefreshTokenExpireTime() {
        return refreshTokenExpireTime;
    }

    public void setRefreshTokenExpireTime(Duration refreshTokenExpireTime) {
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }

    public Duration getRefreshTokenExpireAppTime() {
        return refreshTokenExpireAppTime;
    }

    public void setRefreshTokenExpireAppTime(Duration refreshTokenExpireAppTime) {
        this.refreshTokenExpireAppTime = refreshTokenExpireAppTime;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtPropertiesConfig that = (JwtPropertiesConfig) o;
        return Objects.equals(secretKey, that.secretKey)
                && Objects.equals(accessTokenExpireTime, that.accessTokenExpireTime)
                && Objects.equals(refreshTokenExpireTime, that.refreshTokenExpireTime)
                && Objects.equals(refreshTokenExpireAppTime, that.refreshTokenExpireAppTime)
                && Objects.equals(issuer, that.issuer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secretKey, accessTokenExpireTime, refreshTokenExpireTime, refreshTokenExpireAppTime, issuer);
    }

    @Override
    public String toString() {
        return "JwtPropertiesConfig{" +
                "secretKey='" + secretKey + '\'' +
                ", accessTokenExpireTime=" + accessTokenExpireTime +
                ", refreshTokenExpireTime=" + refreshTokenExpireTime +
                ", refreshTokenExpireAppTime=" + refreshTokenExpireAppTime +
                ", issuer='" + issuer + '\'' +
                '}';
    }
}
