package com.zrcx.dao;

import org.springframework.stereotype.Repository;

@Repository  //bean默认ID名是类名的首字母小写
public class UserDao1 implements IUserDao {

	/* (non-Javadoc)
	 * @see com.zrcx.dao.IUserDao#login(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean login(String username,String password) {
		System.out.println("执行UserDao1.login()................");
		return "abc".equals(username) && "123".equals(password);
	}
}
