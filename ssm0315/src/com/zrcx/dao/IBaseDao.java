package com.zrcx.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao<E> {

	public  List<E> list(Map<String, Object> param);

	public  E getObjById(long id);

	public  int add(E obj);

	//根据ID删除记录
	public  int delete(long id);

	public  int update(E obj);

}