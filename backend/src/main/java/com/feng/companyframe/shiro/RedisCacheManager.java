package com.feng.companyframe.shiro;

import com.feng.companyframe.utils.RedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: RedisCacheManager
 * @Description： 描述
 * @createTime: 2020/2/9 21:14
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class RedisCacheManager implements CacheManager {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new RedisCache<>(s, redisUtil);
    }
}

