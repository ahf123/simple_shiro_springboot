package com.ahf.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahf.shiro.pojo.Users;

@Controller
public class ShiroController {

	// 测试
	@ResponseBody
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	// 测试模板引擎thymeleaf
	@RequestMapping("/testThymeleaf")
	public String testThymeleaf(Model model) {
		// 把数据存入到model
		model.addAttribute("msg", "这是模板引擎");
		System.out.println("thymeleaf");
		return "thymeleaf";

	}

	// 登录页面
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// 登录页面
	// 注意会跳转到favicon.ico图标
	@RequestMapping(value = "/tologin", method = RequestMethod.POST)
	public String tologin(Users user, Model model) {
		// 获取当前对象
		Subject subject = SecurityUtils.getSubject();
		
		// 设置用户和密码的封装
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		// 开启记住我
		//token.setRememberMe(true);
		// 登录
		try {
			subject.login(token);
			model.addAttribute("success", "登录成功");
			return "success";
			//用户名不存在，用户名为空时，不会出错
		} catch (UnknownAccountException e) {
			model.addAttribute("msg", "用户名不存在");
			return "login";
		} catch (IncorrectCredentialsException e) {
			model.addAttribute("msg", "密码错误");
			return "login";
		}catch (Exception e) {
			System.out.println(e.getClass());
			return "login";
		}
	}
	
	@RequestMapping("/logout") //注销
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login";
	}
	
	@RequestMapping("/query")
	public String query(Model model) {
 		model.addAttribute("msg", "我和你");
 		return "query";
	}
}
