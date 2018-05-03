package com.zrcx.dao;

import java.util.List;

import com.zrcx.entity.User;

public interface IUserDao {

	/* (non-Javadoc)
	 * @see com.zrcx.dao.IUser#list(java.lang.String, java.lang.String)
	 */
	public abstract List<User> list(String username, String password);

}