package com.feng.companyframe.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.feng.companyframe.shiro.CustomAccessControlerFilter;
import com.feng.companyframe.shiro.CustomHashedCredentialsMatcher;
import com.feng.companyframe.shiro.CustomRealm;
import com.feng.companyframe.shiro.RedisCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: ShiroConfig
 * @Description： 描述
 * @createTime: 2020/2/8 23:45
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
@Configuration
public class ShiroConfig {

    /**
     * shiro 的 缓存管理器
     * 需要在 CustomRealm bean 中进行设置
     * @return
     */
    @Bean(name = "redisCacheManager")
    public RedisCacheManager redisCacheManager() {
        return new RedisCacheManager();
    }

    /**
     * token 的过滤
     * 需要在 CustomRealm bean 中进行设置
     * @return
     */
    @Bean(name = "customHashedCredentialsMatcher")
    public CustomHashedCredentialsMatcher customHashedCredentialsMatcher() {
        return new CustomHashedCredentialsMatcher();
    }

    /**
     * 登录的 认证域
     *
     * @param customHashedCredentialsMatcher
     * @param redisCacheManager
     * @return
     */
    @Bean(name = "customRealm")
    public CustomRealm getCustomRealm(@Qualifier("customHashedCredentialsMatcher") CustomHashedCredentialsMatcher customHashedCredentialsMatcher,
                                      @Qualifier("redisCacheManager") RedisCacheManager redisCacheManager) {
        CustomRealm customRealm = new CustomRealm();
        // 自定义 处理 token 过滤
        customRealm.setCredentialsMatcher(customHashedCredentialsMatcher);
        // 自定义 缓存管理器
        customRealm.setCacheManager(redisCacheManager);
        return customRealm;
    }

    /**
     * shiro 的安全管理器
     *
     * @param customRealm
     * @return
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("customRealm") CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    /**
     * shiro 的过滤器
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /*
         * 自定义过滤器
         * */
        //自定义拦截器限制并发人数,参考博客：
        LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
        //用来校验token
        filtersMap.put("token", new CustomAccessControlerFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        /*
         * 以下为权限控制
         * */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/index/**", "anon");
        filterChainDefinitionMap.put("/api/user/login", "anon");
        filterChainDefinitionMap.put("/api/user/token", "anon");
        // 以下四个 为 静态资源
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/layui/**", "anon");
        // treetable-lay 的静态资源
        filterChainDefinitionMap.put("/treetable-lay/**", "anon");
        //放开 swagger-ui 地址
        filterChainDefinitionMap.put("/swagger/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/captcha.jpg", "anon");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/csrf", "anon");
        // 拦截所有
        filterChainDefinitionMap.put("/**", "token,authc");

        // 没有登录的用户请求需要登录的页面时自动跳转到登录页面。 配置 shiro 默认登录界面地址，
        shiroFilterFactoryBean.setLoginUrl("/api/user/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 下面两个配置类，开启 shiro aop 注解 支持.
     * 使用代理方式;所以需要开启代码支持;
     * <p>
     * 如果不加 使用 @RequirePermissions 无效
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * shiro 标签方言支持,加上这个之后，可以支持前端 的 shiro标签属性
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}

