package com.zrcx;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.zrcx.common.MybatisUtil;
import com.zrcx.entity.Dict;
/**
 * mybatis的增删改查操作
 * @author zhql
 */
public class Test01 {
	@Test
	public void select01(){
		SqlSession s = MybatisUtil.getFactory().openSession();
		List<Dict> list = s.selectList("DictMapper.select01");
		for (Dict d : list) {
			System.out.println(d);
		}		
	}
	@Test
	public void select02(){
		SqlSession s = MybatisUtil.getFactory().openSession();
		//以Map的形式传参数
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("useFlag", 1);
		param.put("createDate", new Date());
		//param.put("dictName","%SEX");
		param.put("dictName","SYS");
		List<Dict> list = s.selectList("DictMapper.select02",param);
		for (Dict d : list) {
			System.out.println(d);
		}		
	}
	@Test
	public void select03(){
		SqlSession s = MybatisUtil.getFactory().openSession();
		Dict param = new Dict();
		param.setUseFlag(2);
		param.setCreateDate(new Date());
		List<Dict> list = s.selectList("DictMapper.select03",param);
		for (Dict d : list) {
			System.out.println(d);
		}		
	}
	@Test
	public void select04(){//根据ID来查询
		SqlSession s = MybatisUtil.getFactory().openSession();
		//传入参数
		Dict d = s.selectOne("DictMapper.selectById",1);
		System.out.println(d);
		/*Dict d1 = s.selectOne("DictMapper.selectById",1);
		System.out.println(d1);*/
	}
	@Test
	public void insert01(){//新增记录
		//打开会话时，如果参数为true，事务会自动提交，默认手动
		SqlSession s = MybatisUtil.getFactory().openSession(false);
		//传入参数
		Dict d = new Dict();
		d.setDictName("测试数据");
		d.setCkey("KEY");
		d.setCvalue("2");
		d.setUseFlag(2);
		d.setOrderNo(1);
		int i = s.insert("DictMapper.insert01", d);
		System.out.println("i = " + i);
		
		s.commit();//手动提交事务
	}
	@Test
	public void update01(){//更新记录
		//打开会话时，如果参数为true，事务会自动提交，默认手动
		SqlSession s = MybatisUtil.getFactory().openSession(false);
		//传入参数
		Dict d = new Dict();
		d.setId(122L);
		d.setDictName("字典名称");
		d.setCkey("KEY1");
		d.setCvalue("1");
		d.setUseFlag(1);
		d.setOrderNo(10);
		int i = s.update("DictMapper.update01", d);
		System.out.println("i = " + i);
		s.commit();//手动提交事务
	}
	@Test
	public void delete01(){//删除记录
		//打开会话时，如果参数为true，事务会自动提交，默认手动
		SqlSession s = MybatisUtil.getFactory().openSession(false);
		int i = s.delete("DictMapper.delete01",105L);
		System.out.println("i = " + i);
		s.commit();//手动提交事务
	}
}








