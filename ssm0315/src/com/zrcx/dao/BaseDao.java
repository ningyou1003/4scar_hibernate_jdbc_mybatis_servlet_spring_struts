package com.zrcx.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Dao基类，包括Dao常用的5个方法
 * @author Administrator
 *
 */
public class BaseDao<E> extends SqlSessionDaoSupport implements IBaseDao<E>{
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
		//注入mybatis模板      父类方法
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	//获取当前命名空间
	public String getNS(String id){
		String className = this.getClass().getSimpleName();
		String ns = className.substring(0,className.length()-3)+"Mapper." + id;//length-3，减去dao这三个字母
		System.out.println("命名空间.ID:"+ns);
		return ns;
	}
	
	
	
	@Override
	public List<E> list(Map<String,Object> param) {
		String className = this.getClass().getSimpleName();
		String cname = className.substring(0,className.length()-3);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(cname, param);
		List<E> list = this.getSqlSession().selectList(getNS("findList"),map);
		return list;
	}
	
	@Override
	public E getObjById(long id) {
		E obj = this.getSqlSession().selectOne(getNS("findById"), id);
		return obj;
	}

	
	
	@Override
	public int add(E obj) {
		int i = this.getSqlSession().insert(getNS("add"), obj);
		return i;
	}
	//根据ID删除记录
	/* (non-Javadoc)
	 * @see com.zrcx.dao.IBaseDao#delete(long)
	 */
	@Override
	public int delete(long id) {
		int i = this.getSqlSession().delete(getNS("deleteById"));
		return i;
	}

	
	@Override
	public int update(E obj) {
		int i = this.getSqlSession().update(getNS("update"),obj);
		return i;
	}

}





























