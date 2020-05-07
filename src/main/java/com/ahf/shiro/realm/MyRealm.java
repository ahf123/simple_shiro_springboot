package com.ahf.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ahf.shiro.pojo.Users;
import com.ahf.shiro.service.UserService;

/**
 * 自定义shiro的数据源
 * 
 * @author River
 *
 */
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 执行授权逻辑，可以通过查询数据库实现
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取当前用户名称，根据用户名查询角色和权限，多表查询
		String username = (String) principals.getPrimaryPrincipal();
		
		//授权相关的类
		SimpleAuthorizationInfo  info = new SimpleAuthorizationInfo ();
		//设置权限
		if (username.equals("admin")) {
			info.addStringPermission("users:query");
		}else {
			info.addStringPermission("users:add");
		}
		return info;
	}

	/**
	 * 执行认证逻辑 两个问题： 用户名必须判断是否为空，否则出错
	 * 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token1) throws AuthenticationException {
		/**
		 String name = "admin"; String psw = "123";
		 * 
		 * // 编写shiro判断逻辑，判断用户名和密码 // 1.判断用户名 UsernamePasswordToken token =
		 * (UsernamePasswordToken) token1;
		 * 
		 * if (!token.getUsername().equals(name)) { // 用户名不存在 return null;//
		 * shiro底层会抛出UnKnowAccountException }
		 * 
		 * //2.判断密码 SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(name,
		 * psw, this.getName()); return info;
		 */

		// 整合mybaits
		// 1、获取用户信息
		UsernamePasswordToken token = (UsernamePasswordToken) token1;
		Users user = userService.findUserByUserName(token.getUsername());
		// 2、判断用户名
		if (user == null) {
			// 会抛出UnKnownAccoutException
			return null;
		}
		//3、用于比较密码
		AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), this.getName());
        return info;
	}
}
