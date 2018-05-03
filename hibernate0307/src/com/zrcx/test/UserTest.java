package com.zrcx.test;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zrcx.common.HibernateUtil;
import com.zrcx.entity.User;

/**
 * hibernate增删改查操作
 * @author Administrator
 *
 */
public class UserTest {
	static Session s = null;
	@Before
	public void before(){
		System.out.println("---------打开会话--------");
		if(s==null || !s.isOpen()){
			s=HibernateUtil.getSession();
		}
	}
	
	@Test
	public void select01(){//根据ID查询
		User d = (User)s.load(User.class, 3L);  
		System.out.println("对象信息:"+d);
		
	}
	
	@After
	public void after(){
		System.out.println("---------关闭会话--------");
		if (s!=null && s.isOpen()) {
			s.close();
		}
		HibernateUtil.close();
	}
	
}






