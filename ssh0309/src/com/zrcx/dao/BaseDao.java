package com.zrcx.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {
	@Autowired
	private SessionFactory sessionFactory;
	public Session getsSession(){
		
		//return sessionFactory.getCurrentSession();每次打开都是新的会话，不用配置事务
		return sessionFactory.getCurrentSession(); //每次打开都是同一个会话，要在spring中配置事务
		
		
	}
	
	
}





























