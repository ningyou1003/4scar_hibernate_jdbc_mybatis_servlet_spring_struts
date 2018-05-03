package com.zrcx.service;

import com.zrcx.dao.IUserDao;

/**
 * UserDao的包装类（代理类）
 * @author Administrator
 *
 */
public class UserDaoWrapper implements IUserDao {
	private IUserDao userDao;

	@Override
	public boolean login(String username, String password) {
		System.out.println("前置通知");
		boolean is = userDao.login(username, password);
		System.out.println("后置通知");
		return is;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
}








