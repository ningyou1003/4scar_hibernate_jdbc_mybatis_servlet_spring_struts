package com.entor.dao;

import java.util.List;
import java.util.Map;

import com.entor.entity.User;

public interface IUserDao {

	public abstract List<User> list(Map<String, Object> param);

	//新增用户
	public abstract int add(User u);

	//更新
	public abstract int update(User u);

	//删除
	public abstract int delete(long id);

	//根据ID查询
	public abstract User getObjById(long id);

}