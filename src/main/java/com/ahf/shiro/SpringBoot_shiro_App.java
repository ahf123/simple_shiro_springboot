package com.ahf.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 * @author River
 *
 */
@SpringBootApplication
@MapperScan(value = "com.ahf.shiro.mapper")
public class SpringBoot_shiro_App {

	public static void main(String[] args) {
        SpringApplication.run(SpringBoot_shiro_App.class, args);
	}
}
