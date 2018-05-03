package com.entor.dao;

import java.util.List;
import java.util.Map;
import com.entor.entity.Dept;

public interface IDeptDao {

	//列表信息查询
	public List<Dept> select(Map<String, Object> param);
	/**
	 * 新增
	 * @param user
	 * @return
	 */
	public int add(Dept obj);

	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int delete(int id);

	/**
	 * 更新
	 * @param obj
	 * @return
	 */
	public int update(Dept obj);

	/**
	 * 根据ID查询
	 * @param list
	 * @return
	 */
	public Dept getObjById(int id);

}