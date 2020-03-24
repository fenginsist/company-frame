package com.feng.companyframe.shiro;

import com.alibaba.fastjson.JSON;
import com.feng.companyframe.constant.Constant;
import com.feng.companyframe.jwt.JwtTokenUtil;
import com.feng.companyframe.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;


import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisCache
 * @Description： 缓存工具类
 * @createTime: 2020/2/9 20:42
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */

/**
 * 这里的 RedisUtils 不能注入
 *
 * @param <K>
 * @param <V>
 */
@Slf4j
public class RedisCache<K, V> implements Cache<K, V> {

    private final static String PREFIX = "shiro-cache:";
    private String cacheKey;
    private long expire = 24;  // 24 小时

    private RedisUtil redisUtil;

    public RedisCache(String name, RedisUtil redisUtil) {
//        this.cacheKey=PREFIX+name+":";
        this.cacheKey = Constant.IDENTIFY_CACHE_KEY;
        this.redisUtil = redisUtil;
    }


    /**
     * 根据 key 值 获取 权限信息
     *
     * @param key  jwt
     * @return
     * @throws CacheException
     */
    @Override
    public V get(K key) throws CacheException {
        log.info("Shiro 从缓存中获取数据 KEY 值[{}]", key);
        if (key == null) {
            return null;
        }
        try {
            String redisCacheKey = getRedisCacheKey(key);
            Object rawValue = redisUtil.get(redisCacheKey);  // 根据key 获取 数据
            if (rawValue == null) {
                return null;
            }
            SimpleAuthorizationInfo info = JSON.parseObject(rawValue.toString(), SimpleAuthorizationInfo.class);
            V value = (V) info;
            return value;
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    /**
     * 存值
     *
     * @param key jwt
     * @param value
     * @return
     * @throws CacheException
     */
    @Override
    public V put(K key, V value) throws CacheException {
        log.info("put key [{}]", key);
        if (key == null) {
            log.warn("Saving a null key is meaningless, return value directly without call Redis.");
            return value;
        }
        try {
            String redisCacheKey = getRedisCacheKey(key); // cacheKey + userId
            redisUtil.set(redisCacheKey, value != null ? value : null, expire, TimeUnit.HOURS);
            return value;
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    /**
     * 根据 key 值 删除缓存的值
     *
     * @param key
     * @return
     * @throws CacheException
     */
    @Override
    public V remove(K key) throws CacheException {
        log.info("remove key [{}]", key);
        if (key == null) {
            return null;
        }
        try {
            String redisCacheKey = getRedisCacheKey(key);
            Object rawValue = redisUtil.get(redisCacheKey);
            V previous = (V) rawValue;
            redisUtil.delete(redisCacheKey);
            return previous;
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    /**
     * 清除 所有的值
     *
     * @throws CacheException
     */
    @Override
    public void clear() throws CacheException {
        log.debug("clear cache");
        Set<String> keys = null;
        try {
            keys = redisUtil.keys(this.cacheKey + "*");
        } catch (Exception e) {
            log.error("get keys error", e);
        }
        if (keys == null || keys.size() == 0) {
            return;
        }
        for (String key : keys) {
            redisUtil.delete(key);
        }
    }

    /**
     * 获取 redis 所存的 缓存数的大小
     *
     * @return
     */
    @Override
    public int size() {
        int result = 0;
        try {
            result = redisUtil.keys(this.cacheKey + "*").size();
        } catch (Exception e) {
            log.error("get keys error", e);
        }
        return result;
    }

    /**
     * 获取key值
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keys() {
        Set<String> keys = null;
        try {
            keys = redisUtil.keys(this.cacheKey + "*");
        } catch (Exception e) {
            log.error("get keys error", e);
            return Collections.emptySet();
        }
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptySet();
        }
        Set<K> convertedKeys = new HashSet<>();
        for (String key : keys) {
            try {
                convertedKeys.add((K) key);
            } catch (Exception e) {
                log.error("deserialize keys error", e);
            }
        }
        return convertedKeys;
    }

    /**
     * 获取 value值
     *
     * @return
     */
    @Override
    public Collection<V> values() {
        Set<String> keys = null;
        try {
            keys = redisUtil.keys(this.cacheKey + "*");
        } catch (Exception e) {
            log.error("get values error", e);
            return Collections.emptySet();
        }
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptySet();
        }
        List<V> values = new ArrayList<V>(keys.size());
        for (String key : keys) {
            V value = null;
            try {
                value = (V) redisUtil.get(key);
            } catch (Exception e) {
                log.error("deserialize values= error", e);
            }
            if (value != null) {
                values.add(value);
            }
        }
        return Collections.unmodifiableList(values);
    }

    /**
     * 获取 redis 中的 缓存 key  ,很重要，
     *
     * @param key
     * @return
     */
    private String getRedisCacheKey(K key) {
        if (null == key) {
            return null;
        } else {
            return this.cacheKey + JwtTokenUtil.getUserId(key.toString());
        }
    }
}

