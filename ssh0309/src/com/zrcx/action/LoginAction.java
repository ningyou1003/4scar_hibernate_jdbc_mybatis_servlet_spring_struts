package com.zrcx.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.zrcx.dao.IUserDao;
import com.zrcx.entity.User;
@Controller
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private User user;
	private String msg;
	@Autowired
	private IUserDao userDao;
	
	@Override
	public String execute() throws Exception {
		System.out.println("-------执行execute()--------");
		System.out.println("用户信息:" + user);
		List<User> list = 
				userDao.list(user.getUsername(), user.getPassword());
		if (list.size()>0) {
			this.msg="登录成功";
		}else{
			this.msg="登录失败";
		}
		return "index";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}