package com.ahf.shiro.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ahf.shiro.pojo.Users;

//@Mapper  //必须存在，并且映射文件在同一个包下
public interface UserDao {
	 Users findUserByUserName(String username);
}
