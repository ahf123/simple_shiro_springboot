package com.ahf.shiro.text;

public class Text {
   /**
DROP DATABASE IF EXISTS shiro_springboot;
CREATE DATABASE shiro_springboot CHARACTER SET utf8;

USE shiro_springboot
#用户表
CREATE TABLE users(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   username VARCHAR(50),
   PASSWORD VARCHAR(50)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
SELECT * FROM users 
INSERT INTO users(username,PASSWORD)VALUES('admin','123456'),('guest','123456')

SELECT * FROM users WHERE username = 'admin'

#角色表
CREATE TABLE roles(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   rolename VARCHAR(50)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO roles(rolename)VALUES('zhangsan'),('lisi')
SELECT * FROM roles 

#权限表
CREATE TABLE permssions(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   permssionname VARCHAR(50)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO permssions(permssionname)VALUES('user:query'),('user:add')
SELECT * FROM permssions 

#用户角色表
CREATE TABLE users_roles(
   id INT PRIMARY KEY AUTO_INCREMENT,#主键自增
    users_id INT REFERENCES users(id),
    roles_id INT REFERENCES roles(id),
    UNIQUE(users_id,roles_id) # 唯一
)ENGINE=INNODB DEFAULT CHARSET=utf8;

#角色权限
CREATE TABLE roles_permssions(
    id INT PRIMARY KEY AUTO_INCREMENT,#主键自增
    permssions_id INT REFERENCES permssions(id),
    roles_id INT REFERENCES roles(id),
    UNIQUE(permssions_id,roles_id) # 唯一
)ENGINE=INNODB DEFAULT CHARSET=utf8;
    */
}
