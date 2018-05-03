package com.zrcx;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zrcx.common.MybatisUtil;
import com.zrcx.entity.Student;
/**
 * mybatis的动态SQL查询
 * @author zhql
 */
public class Test04 {
	@Test
	public void select01(){//根据学生获取班级信息
		SqlSession s = MybatisUtil.getFactory().openSession();
		Student p = new Student();
		//p.setSex(2);
		List<Student> list = s.selectList("StudentMapper.select01",p);
		for (Student st : list) {
			System.out.println(st);
		}	
	}
	@Test
	public void selectForPage(){//分页查询
		SqlSession s = MybatisUtil.getFactory().openSession();
		Student p = new Student();
		//物理分页,效率低
		//RowBounds rb = new RowBounds(5, 5);
		//p.setSex(2);
		//List<Student> list = s.selectList("StudentMapper.select01",p,rb);
		//通过分页插件实现逻辑分页,第一个参数是页码，第二参数是页记录数，第三个参数 true表示要查总数
		PageHelper.startPage(2,3,true);
		List<Student> list = s.selectList("StudentMapper.select01",p);
		Page<Student> plist = ((Page<Student>)list);
		System.out.println("总记录数:" + plist.getTotal());
		for (Student st : plist) {
			System.out.println(st);
		}	
	}
	@Test
	public void update01(){
		SqlSession s = MybatisUtil.getFactory().openSession();
		Student p = new Student();
//		p.setSex(1);
//		p.setBirthday(new Date());
		p.setId(31L);
		int i = s.update("StudentMapper.update01",p);
		System.out.println("i=" + i);
	}
	
}








