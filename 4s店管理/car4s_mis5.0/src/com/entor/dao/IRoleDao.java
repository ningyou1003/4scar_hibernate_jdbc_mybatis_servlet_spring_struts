package com.entor.dao;

import java.util.List;
import java.util.Map;
import com.entor.entity.Role;

public interface IRoleDao {

	//列表信息查询
	public List<Role> list(Map<String, Object> param);
	/**
	 * 新增
	 * @param user
	 * @return
	 */
	public int add(Role obj);
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
	public int update(Role obj);

	/**
	 * 根据ID查询
	 * @param list
	 * @return
	 */
	public Role getObjById(int id);

}