package com.zrcx.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zrcx.common.HibernateUtil;
import com.zrcx.entity.Role;
import com.zrcx.entity.Student;
import com.zrcx.entity.Tclass;

/**
 * hibernate增删改查操作
 * @author Administrator
 *
 */
public class StudentTest {
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
		Student d = (Student)s.load(Student.class, 3L);  
		System.out.println("对象信息:"+d);
		
	}
	@Test
	public void select02(){//级联查询
		Student d = (Student)s.load(Student.class, 3L);  
		System.out.println("学生信息:"+d);
		Tclass tc = d.getTclass();
		System.out.println("班级信息：" + tc);
	}
	
	@Test
	public void add(){//新增
		Transaction tx = s.beginTransaction();
		Student s1 = new Student();
		s1.setBirthday(new Date());
		s1.setCreateDate(new Date());
		s1.setName("小二1");
		s1.setSex(2);
		s1.setSnative("广西");
		Tclass tc = new Tclass();
		tc.setId(7L);
		s1.setTclass(tc);
		s.save(s1);
		tx.commit();
	}
	
	
	@Test
	public void update(){//更新
		Transaction tx = s.beginTransaction();
		Student tc = (Student) s.get(Student.class, 100L);
		tc.setName("小2");
		tc.setSex(1);
		Tclass t = new Tclass();
		t.setId(8L);
		tc.setTclass(t);
		tx.commit();
	}
	
	@Test
	public void delete(){//删除
		Transaction tx = s.beginTransaction();
		Student tc = (Student) s.get(Student.class, 100L);
		s.delete(tc);
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






