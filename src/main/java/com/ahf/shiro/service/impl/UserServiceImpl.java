package com.ahf.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahf.shiro.mapper.UserDao;
import com.ahf.shiro.pojo.Users;
import com.ahf.shiro.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public Users findUserByUserName(String username) {
		System.out.println(userDao);
		return userDao.findUserByUserName(username);
	}
}
