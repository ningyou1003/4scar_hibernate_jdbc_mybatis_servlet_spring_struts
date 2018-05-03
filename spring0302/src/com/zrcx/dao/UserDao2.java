package com.zrcx.dao;

import org.springframework.stereotype.Repository;

@Repository("userDao")  //指定bean的名称   bean默认ID名是类名的首字母小写
public class UserDao2 implements IUserDao {

	public boolean login(String username,String password) {
		System.out.println("执行UserDao2.login()................");
		return "abc".equals(username) && "123".equals(password);
	}
}
