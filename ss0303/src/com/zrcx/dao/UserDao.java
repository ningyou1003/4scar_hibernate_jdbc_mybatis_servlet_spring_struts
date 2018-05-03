package com.zrcx.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	public boolean login(String username,String password){
		
		System.out.println("执行UserDao...");
		return "abc".equals(username) && "123".equals(password);
				
	}
}
