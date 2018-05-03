package com.zrcx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zrcx.dao.IUserDao;
import com.zrcx.dao.UserDao1;
import com.zrcx.dao.UserDao2;
import com.zrcx.service.JDKProxy;
import com.zrcx.service.UserDaoWrapper;
import com.zrcx.service.UserService;

/**
 * 代理类测试
 * @author Administrator
 *
 */
public class Test03 {
	public static void main(String[] args) {
//		UserDao2 dao = new UserDao2();
		/*UserDaoWrapper daow = new UserDaoWrapper();
		daow.setUserDao(dao);
		boolean is = daow.login("abc", "123");
		System.out.println("执行结果："+is);*/
		
		/*//JDK动态代理
		JDKProxy jp = new JDKProxy();
		//创建动态代理对象
		IUserDao obj = (IUserDao) jp.createProxyInstance(dao);
		boolean is = obj.login("1", "1");
		System.out.println("执行结果："+is);*/
		
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("applicationContext03.xml");
		UserService us = (UserService)ac.getBean("userService");
		boolean is = us.login("1","1");
		System.out.println("登录状态：" + is);
		
	}
}















