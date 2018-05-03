package com.zrcx.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.zrcx.dao.UserDao;
@Controller
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	@Autowired
	private UserDao userDao;

	@Override
	public String execute() throws Exception {
		System.out.println("执行LoginAction....");
		boolean is = userDao.login(username, password);
		System.out.println(is?"登录成功":"登录失败");
		return "index";
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	
}
