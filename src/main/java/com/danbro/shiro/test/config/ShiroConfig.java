package com.danbro.shiro.test.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.danbro.shiro.test.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Classname ShiroConfig
 * @Description TODO shrio配置类
 * @Date 2019/12/26 10:31
 * @Author Danrbo
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("webSecurityManager")DefaultWebSecurityManager webSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //配置webSecurityManager
        shiroFilterFactoryBean.setSecurityManager(webSecurityManager);
        /**
         * shiro内置的过滤器，可以实现权限相关的拦截器
         * 常用的过滤器
         * anon:无需认证（登录）可以访问
         * authc:必须认证才可以访问
         * user;如果需要rememberMe的功能可以访问
         * perms:该资源必须得到资源权限才可以访问
         * role:该资源必须得到角色权限才可以访问
         *
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        //设置/user/*的页面都需要认证才能登陆
        //认证好的用户无法访问add页面，并且会自动跳转到设置好的unauth页面，perms[user:add]里的perms表示权限，user表示user这个用户 add表示add这个行为
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        filterMap.put("/user/*","authc");
        //配置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "webSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realm")UserRealm userRealm){
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        //配置realm
        webSecurityManager.setRealm(userRealm);
        return webSecurityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "realm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    /**
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
