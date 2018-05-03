package com.zrcx;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.zrcx.common.MybatisUtil;
import com.zrcx.entity.Student;
/**
 * mybatis的多表关联查询（多对一）
 * @author zhql
 */
public class Test03 {
	@Test
	public void select01(){//根据学生获取班级信息
		SqlSession s = MybatisUtil.getFactory().openSession();
		Student st = s.selectOne("StudentMapper.getObjById1",1L);
		System.out.println("学生信息:" + st);
		System.out.println("班级信息:" + st.getTclass());			
	}
	@Test
	public void select02(){
		SqlSession s = MybatisUtil.getFactory().openSession();
		Student st = s.selectOne("StudentMapper.getObjById2",1L);
		System.out.println("学生信息:" + st);
		System.out.println("班级信息:" + st.getTclass());	
	}
	
}








