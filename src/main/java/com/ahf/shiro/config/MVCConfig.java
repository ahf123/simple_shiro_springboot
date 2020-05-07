package com.ahf.shiro.config;

import org.springframework.web.servlet.HandlerExceptionResolver;

import com.ahf.shiro.exception.MyExceptionResolver;

public class MVCConfig {
	
    public HandlerExceptionResolver getExceptionResolver() {
		
		
    	return new MyExceptionResolver();
    }
}
