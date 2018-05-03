package com.zrcx;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.zrcx.common.MybatisUtil;
import com.zrcx.entity.Student;
import com.zrcx.entity.Tclass;
/**
 * mybatis的多表关联查询
 * @author zhql
 */
public class Test02 {
	@Test
	public void select01(){
		SqlSession s = MybatisUtil.getFactory().openSession();
		Tclass tc = s.selectOne("TclassMapper.getObjById1",1L);
		System.out.println("班级信息:" + tc);
		for (Student d : tc.getStudents()) {
			System.out.println(d);
		}		
	}
	@Test
	public void select02(){
		SqlSession s = MybatisUtil.getFactory().openSession();
		Tclass tc = s.selectOne("TclassMapper.getObjById2",1L);
		System.out.println("班级信息:" + tc);
		for (Student d : tc.getStudents()) {
			System.out.println(d);
		}		
	}
	
}








