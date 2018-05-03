package com.entor.dao;

import java.util.List;
import java.util.Map;

import com.entor.entity.Dict;

public interface IDictDao {

	/**
	 * 查询对象列表
	 * @return
	 */
	public abstract List<Dict> list(Map<String, Object> param);

	/**
	 * 根据ID查询对象
	 * @param id
	 * @return
	 */
	public abstract Dict getObjById(long id);

	/**
	 * 新增
	 * @param tc
	 * @return
	 */
	public abstract int add(Dict tc);

	/**
	 * 删除
	 * @param Id
	 * @return
	 */
	public abstract int delete(long id);

	/**
	 * 更新
	 * @param user
	 * @return
	 */
	public abstract int update(Dict tc);

}