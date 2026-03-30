package com.feng.companyframe.jwt;

import com.feng.companyframe.constant.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.util.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName: JwtTokenUtil
 * @Description： JWT token 工具类
 * @createTime: 2020/2/3 23:24
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Slf4j
public class JwtTokenUtil {
    private static String secretKey;
    private static Duration accessTokenExpireTime;  // java.time.Duration;
    private static Duration refreshTokenExpireTime;
    private static Duration refreshTokenExpireAppTime;
    private static String issuer;

    /**
     *  读取配置文件类中属性，放到本文件中
     * @param jwtPropertiesConfig
     */
    public static void setTokenProperties(JwtPropertiesConfig jwtPropertiesConfig) {
        secretKey = jwtPropertiesConfig.getSecretKey();
        accessTokenExpireTime = jwtPropertiesConfig.getAccessTokenExpireTime();
        refreshTokenExpireTime = jwtPropertiesConfig.getRefreshTokenExpireTime();
        refreshTokenExpireAppTime = jwtPropertiesConfig.getRefreshTokenExpireAppTime();
        issuer = jwtPropertiesConfig.getIssuer();
    }

    /**
     * 生成 access_token
     *
     * @param subject 主题
     * @param claims  存储在JWT里面的信息 一般放些用户的权限/角色信息
     * @return
     */
    public static String getAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(issuer, subject, claims, accessTokenExpireTime.toMillis(), secretKey);
    }

    /**
     * 生产 PC refresh_token(PC 端过期时间短一些)
     *
     * @param subject
     * @param claims
     * @return
     */
    public static String getRefreshToken(String subject, Map<String, Object> claims) {
        return generateToken(issuer, subject, claims, refreshTokenExpireTime.toMillis(), secretKey);
    }

    /**
     * 生产 App端 refresh_token
     *
     * @param subject
     * @param claims
     * @return
     */
    public static String getRefreshAppToken(String subject, Map<String, Object> claims) {
        return generateToken(issuer, subject, claims, refreshTokenExpireAppTime.toMillis(), secretKey);
    }

    /**
     * 生成token， 以上三个方法 都调用此方法
     *
     * @param issuer    签发人
     * @param subject   主题
     * @param claims    存储在JWT里面的信息 一般放些用户的权限/角色信息
     * @param ttlMillis 有效时间(毫秒) --》配置文件获取
     * @param secret    秘钥  --》配置文件获取
     * @return 返回 token java.lang.String
     */
    public static String generateToken(String issuer, String subject, Map<String, Object> claims,
                                       long ttlMillis, String secret) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMills = System.currentTimeMillis();
        Date now = new Date(nowMills);

        // 对秘钥进行编码 成数组
        byte[] signingKey = DatatypeConverter.parseBase64Binary(secret);

        // 创建 jwt 构造器
        JwtBuilder builder = Jwts.builder();
        if (null != claims) {
            builder.setClaims(claims);
        }
        if (!StringUtils.isEmpty(subject)) {
            builder.setSubject(subject);
        }
        if (!StringUtils.isEmpty(issuer)) {
            builder.setIssuer(issuer);
        }
        // 主题在 此时 进行时间设定
        builder.setIssuedAt(now);
        if (nowMills >= 0) {
            long expMillis = nowMills + ttlMillis;
            Date exp = new Date(expMillis);
            // 设置过期时间
            builder.setExpiration(exp);
        }
        // 拿着 算法和 秘钥进行 签名
        builder.signWith(signatureAlgorithm, signingKey);
        // 返回 jwt
        return builder.compact();
    }

// 以下为 对token 的操作静态方法

    /**
     * 从令牌中获取 数据声明 Claims
     *
     * @param token 传入的 jwt
     * @return io.jsonwebtoken.Claims
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 获取用户id
     *
     * @param token
     * @return io.jsonwebtoken.Claims
     */
    public static String getUserId(String token) {
        String userId = null;
        try {
            Claims claims = getClaimsFromToken(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            log.error("eror={}", e.getLocalizedMessage());
        }
        return userId;
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return username String
     */
    public static String getUserName(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            username = (String) claims.get(Constant.JWT_USER_NAME);
        } catch (Exception e) {
            log.error("eror={}", e);
        }
        return username;
    }

    /**
     * 验证token 是否过期(true:已过期 false:未过期)
     *
     * @param token
     * @return java.lang.Boolean
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            // token的过期时间 与 当前时间比较，如果小于，则为 true，为过期
            return expiration.before(new Date());
        } catch (Exception e) {
            log.error("error={}", e.getLocalizedMessage());
            return true;
        }
    }

    /**
     * 校验令牌(true：验证通过 false：验证失败)
     *
     * @param token
     * @return Boolean
     */
    public static Boolean validateToken(String token) {
        Claims claimsFromToken = getClaimsFromToken(token);
        //
        return (null != claimsFromToken && !isTokenExpired(token)); //  && ：and 都为true
    }

    /**
     * 刷新token
     *
     * @param refreshToken
     * @param claims       主动去刷新的时候 改变JWT payload 内的信息
     * @return String
     */
    public static String refreshToken(String refreshToken, Map<String, Object> claims) {
        String refreshedToken;
        try {
            Claims parserclaims = getClaimsFromToken(refreshToken);
            /**
             * 刷新token的时候如果为空说明原先的 用户信息不变 所以就引用上个token里的内容
             */
            if (null == claims) {
                claims = parserclaims;
            }
            refreshedToken = generateToken(parserclaims.getIssuer(),
                    parserclaims.getSubject(), claims, accessTokenExpireTime.toMillis(), secretKey);
        } catch (Exception e) {
            refreshedToken = null;
            log.error("error={}", e.getLocalizedMessage());
        }
        return refreshedToken;
    }


    /**
     * 获取token的剩余过期时间
     *
     * @param token
     * @return
     */
    public static long getRemainingTime(String token){
        long result=0;
        try {
            long nowMillis = System.currentTimeMillis();
            result= getClaimsFromToken(token).getExpiration().getTime()-nowMillis;
        } catch (Exception e) {
            log.error("error={}",e.getLocalizedMessage());
        }
        return result;
    }

}
