package com.zrcx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zrcx.service.UserService;

public class Test02 {
	public static void main(String[] args) {
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("applicationContext02.xml");
		UserService us = (UserService)ac.getBean("userService");
		boolean is = us.login("1","1");
		System.out.println("登录状态：" + is);
	}
}












