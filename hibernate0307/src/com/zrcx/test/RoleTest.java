package com.zrcx.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zrcx.common.HibernateUtil;
import com.zrcx.entity.Menu;
import com.zrcx.entity.Role;

/**
 * 多对多
 * @author Administrator
 *
 */
public class RoleTest {
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
		Role r = (Role)s.load(Role.class, 1L);  
		System.out.println("角色信息:"+r);
		Set<Menu> menus = r.getMenus();
		for (Menu m : menus) {
			System.out.println(m);
		}
	}

	@Test
	public void add(){//新增
		Transaction tx = s.beginTransaction();
		Role r1 = new Role();
		r1.setName("学生角色");
		Set<Menu> menus = new HashSet<Menu>();
		Menu m1 = new Menu();
		m1.setId(2L);
		menus.add(m1);
		Menu m2 = new Menu();
		m2.setId(3L);
		menus.add(m2);
		r1.setMenus(menus);
		s.save(r1);
		tx.commit();
	}
	
	@Test
	public void update(){//更新
		Transaction tx = s.beginTransaction();
		Role r1 = (Role) s.get(Role.class, 4L);
		Set<Menu> menus = r1.getMenus();
		Menu m1 = new Menu();
		m1.setId(4L);
		menus.add(m1);
		Menu m2 = new Menu();
		m2.setId(5L);
		menus.add(m2);
		tx.commit();
	}

	@Test
	public void delete(){//删除
		Transaction tx = s.beginTransaction();
		Role r1 = (Role) s.get(Role.class, 4L);
		s.delete(r1);
		tx.commit();
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






