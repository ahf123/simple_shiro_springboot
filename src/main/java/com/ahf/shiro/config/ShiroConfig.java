package com.ahf.shiro.config;

import java.util.LinkedHashMap;

import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ahf.shiro.realm.MyRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {
	/**
	 * shiro是基于过滤连拦截
	 *      1、自定义数据源realm(AuthorizingRealm)
	 *      2、核心管理：securityManager(DefaultWebSecurityManager)
	 *      3、过滤链：ShiroFilterFactoryBean(ShiroFilter)
	 * 
	 *           添加shiro内置过滤器 shiro内置过滤器可以实现
	 *          权限相关的拦截器 常用的过滤器：
	 *        anon: 无需认证(登录)可以访问
	 *        authc: 必须认证才可以访问 
	 *        user: 如果使用
	 *        remenber的功能就直接访问
	 *        perms: 该资源必须得到资源权限才可以访问 
	 *        role: 该资源必须得到角色权限才可以访问
	 */
	@Bean // 加入容器
	public ShiroFilterFactoryBean getshiFactoryBean(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 设置安全管理核心
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 过滤规则
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录失败页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthor");

		// shiro过滤器请求规则
		LinkedHashMap<String, String> filteMap = new LinkedHashMap<String, String>();
		
		// 提交登录信息必须能够匿名访问
		
		filteMap.put("/tologin","anon");
		filteMap.put("/index","authc");
		//filteMap.put("/logut","authc");
		filteMap.put("/testThymeleaf","anon");
		
		//授权过滤器
		//当前授权拦截后，shiro会自动跳转到未授权页面
		filteMap.put("/query","perms[users:query]");
		
		filteMap.put("/*","authc"); //表示一级路径下的所有必须登录
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filteMap);
		return shiroFilterFactoryBean;
	}

	@Bean // 核心管理
	public DefaultWebSecurityManager securityManager(@Qualifier("myrealm") AuthorizingRealm realm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 关联数据链，实现认证授权
		securityManager.setRealm(realm);
		return securityManager;
	}

	@Bean("myrealm") // 注入数据源
	public AuthorizingRealm getRealm() {
		return new MyRealm();
	}
	
	//配置ShiroDialect，用于thymeleaf和shiro标签配合使用
	@Bean
	public ShiroDialect getShiroDialect() {
		return new ShiroDialect();
	}
	
}
