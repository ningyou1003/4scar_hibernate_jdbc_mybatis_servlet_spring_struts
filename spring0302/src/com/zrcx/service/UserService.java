package com.zrcx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrcx.dao.IUserDao;
@Service   //自动扫描的注释，扫描到就纳入spring馆
public class UserService {
	@Autowired    //配置了注解，可以不用get和set方法     代替;xml配置文件中，通过在bean节点下配置
	private IUserDao userDao;

	public boolean login(String username,String password) {
		System.out.println("执行UserService.login()................");
		return userDao.login(username,password);
	}

	/*public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
*/
	
}










