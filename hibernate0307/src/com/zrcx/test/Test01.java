package com.zrcx.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.zrcx.common.HibernateUtil;
import com.zrcx.entity.Dict;

/**
 * hibernate增删改查操作
 * @author Administrator
 *
 */
public class Test01 {
	Session s = null;
	@Before
	public void before(){
		System.out.println("---------打开会话--------");
		s = HibernateUtil.getSession();
	}
	
	@Test
	public void select01(){//根据ID查询
		//根据ID查询对象（立即查询）
		//Dict d = (Dict) s.get(Dict.class, 1L);  //Dict类名
		//根据ID查询对象（懒加载），必须会话打开状态下查询
		Dict d = (Dict)s.load(Dict.class, 1L);  //d对象没调用就不执行，调用了才执行
		//s.close();lazyInit,no-session异常,懒加载没有打开会话时的异常
		System.out.println("对象信息:"+d);
		
		//清空所有缓存
		//s.clear();
		//删除指定对象的缓存
		s.evict(d);
		d = (Dict)s.load(Dict.class, 1L);  
		System.out.println("对象信息:"+d);//有缓存，hql语句打印一次，没缓存，打印两次
		s.clear();
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void select02(){//使用hql查询，实现分页
		String hql = "from Dict t where t.useFlag=1";//不能写select * from 不认
		Query q = s.createQuery(hql);
		//分页
		q.setFirstResult(5);//从第几条开始
		q.setMaxResults(5);//获取多少条
		List<Dict> list = q.list();
		for (Dict d : list) {
			System.out.println(d);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void select03(){//使用HQL查询指定的列
		String hql = "select count(*),t.dictName from Dict t group by t.dictName ";
		Query q = s.createQuery(hql);
		List<Object[]> list = q.list();//每一个行对应一个object数组
		for (Object[] d : list) {
			System.out.println(d[1] + ":" + d[0]);
		}
	}
	
	@Test
	public void select04(){//根据ID查询
		
		Dict d = (Dict)s.load(Dict.class, 1L);  //d对象没调用就不执行，调用了才执行
		Hibernate.initialize(d);//强制初始化
	}
	
	
	@Test
	public void select05(){//使用criteria做限制查询
		Criteria c = s.createCriteria(Dict.class);
		//c.add(Restrictions.eq("dictName", "SYS_SEX"));//and查询
		//c.add(Restrictions.like("dictName", "SYS",MatchMode.ANYWHERE));//模糊查询
		//c.add(Restrictions.eq("useFlag", 1));
		
		c.add(//or操作
		Restrictions.or(
				Restrictions.like("dictName", "SYS",MatchMode.ANYWHERE), 
				Restrictions.eq("useFlag", 1)));
		
		//排序
		c.addOrder(Order.desc("createDate"));
		
		List<Dict> list = c.list();
		for (Dict d : list) {
			System.out.println(d);
		}
		
	}
	
	@Test
	public void add01(){//新增对象
		Transaction tx = s.beginTransaction();//打开事务
		Dict dict = new Dict();
		dict.setCkey("TEMP");
		dict.setCvalue("1");
		dict.setCreateDate(new Date());
		dict.setDictName("测试数据");
		dict.setUseFlag(1);
		dict.setOrderNo(10);
		s.save(dict);
		tx.commit();//提交事务
		
	}
	
	@Test
	public void update01(){//更新对象
		Transaction tx = s.beginTransaction();//打开事务
		Dict dict = (Dict)s.get(Dict.class,141L);
		dict.setCvalue("2");
		dict.setUseFlag(2);
		dict.setOrderNo(1);
		s.update(dict);
		tx.commit();//提交事务
	}
	
	@Test
	public void update02(){//用HQL更新对象
		Transaction tx = s.beginTransaction();//打开事务
		String hql = "update Dict t set t.dictName='测试数据11' where id = 141";
		Query q = s.createQuery(hql);
		int i = q.executeUpdate();
		System.out.println("更新记录数:" + i);
		tx.commit();//提交事务
	}
	
	@Test
	public void update03(){//用HQL更新对象，给HQL传递参数
		Transaction tx = s.beginTransaction();//打开事务
		String hql = "update Dict t set t.dictName=?,t.createDate=? where id =?";
		Query q = s.createQuery(hql);
		//占位符，从左到右 0,1,2...
		q.setString(0, "测试数据11");
		q.setDate(1, new Date());
		q.setLong(2, 142L);
		int i = q.executeUpdate();
		System.out.println("更新记录数:" + i);
		tx.commit();//提交事务
	}
	
	@Test
	public void update04(){//用HQL更新对象，根据命名给HQL传递参数
		Transaction tx = s.beginTransaction();//打开事务
		String hql = "update Dict t set t.dictName=:a,t.createDate=:b where id =:c";
		Query q = s.createQuery(hql);
		//根据参数名来传值
		q.setString("a", "测试数据22");
		q.setDate("b", new Date());
		q.setLong("c", 142L);
		int i = q.executeUpdate();
		System.out.println("更新记录数:" + i);
		tx.commit();//提交事务
	}
	
	@Test
	public void update05(){//更新游离对象
		Transaction tx = s.beginTransaction();//打开事务
		String hql = "update Dict t set t.dictName=:a,t.createDate=:b where id =:c";
		Query q = s.createQuery(hql);
		//根据参数名来传值
		q.setString("a", "测试数据22");
		q.setDate("b", new Date());
		q.setLong("c", 142L);
		int i = q.executeUpdate();
		System.out.println("更新记录数:" + i);
		tx.commit();//提交事务
	}
	
	@Test
	public void delete01(){//删除对象
		Transaction tx = s.beginTransaction();//打开事务
		Dict dict = (Dict)s.get(Dict.class, 141L);
		s.delete(dict);
		tx.commit();//提交事务
	}
	
	@Test
	public void delete02(){//删除对象
		Transaction tx = s.beginTransaction();//打开事务
		String hql = "delete Dict t where id = 141";
		Query q = s.createQuery(hql);
		int i = q.executeUpdate();
		System.out.println("删除记录数:" + i);
		tx.commit();//提交事务
	}
	
	public void callProc(){//hibernate调用存储过程
		//创建一个实现一个work接口匿名类的对象
		Work work = new Work(){
			@Override
			public void execute(Connection conn) throws SQLException {
				CallableStatement cs = conn.prepareCall("{call proc_test01()}");
				cs.execute();
			}
			
		};
		//执行
		s.doWork(work);
	}
	
	@After
	public void after(){
		System.out.println("---------关闭会话--------");
		s.close();
		HibernateUtil.close();
	}
	
}























