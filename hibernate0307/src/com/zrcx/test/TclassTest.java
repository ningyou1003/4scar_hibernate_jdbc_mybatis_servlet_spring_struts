package com.zrcx.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zrcx.common.HibernateUtil;
import com.zrcx.entity.Student;
import com.zrcx.entity.Tclass;

/**
 * hibernate增删改查操作
 * @author Administrator
 *
 */
public class TclassTest {
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
		Tclass d = (Tclass)s.load(Tclass.class, 1L);  
		System.out.println("对象信息:"+d);
		Set<Student> set = d.getStudents();
		for (Student s : set) {
			System.out.println(s);
		}
	}
	
	@Test
	public void add(){//新增
		Transaction tx = s.beginTransaction();
		Tclass tc = new Tclass();
		tc.setName("测试班级");
		tc.setCharger("小二");
		tc.setCreateDate(new Date());
		Set<Student> set = new HashSet<Student>();
		Student s1 = new Student();
		s1.setId(100L);
		set.add(s1);
		Student s2 = new Student();
		s2.setId(110L);
		set.add(s2);
		tc.setStudents(set);
		
		s.save(tc);
		tx.commit();
	}
	
	
	@Test
	public void add1(){//级联新增
		Transaction tx = s.beginTransaction();
		Tclass tc = new Tclass();
		tc.setName("测试班级");
		tc.setCharger("小二");
		tc.setCreateDate(new Date());
		Set<Student> set = new HashSet<Student>();
		Student s1 = new Student();
		s1.setBirthday(new Date());
		s1.setCreateDate(new Date());
		s1.setName("学生姓名");
		s1.setSex(2);
		
		s.save(tc);
		tx.commit();
	}
	
	@Test
	public void update(){//更新
		Transaction tx = s.beginTransaction();
		Tclass tc = (Tclass) s.get(Tclass.class, 118L);//获得数据库的对象，持久态
		tc.setName("测试班级yi");
		
		Set<Student> set = tc.getStudents();
		Student s1 = (Student) s.get(Student.class, 110L);
		set.remove(s1);
		
		Student s2 = new Student();
		s2.setId(31L);
		set.add(s2);
		tc.setStudents(set);
		
		s.save(tc);
		tx.commit();
	}
	
	
	@Test
	public void delete(){//删除
		Transaction tx = s.beginTransaction();
		Tclass t = (Tclass) s.get(Tclass.class, 112L);
		s.delete(t);
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
	
	public static void main(String[] args) {
		s=HibernateUtil.getSession();
		Tclass d = (Tclass)s.load(Tclass.class, 1L);
		/*Query q=s.createQuery(" from Tclass where id = 1");
		List list = q.list();*/
		
		System.out.println("对象信息:"+d);
	}
	
}






