package com.zrcx.action;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;//版本号，
	private String username;
	private String password;
	private String msg;
	
	
	
	@Override
	public String execute() throws Exception {
		System.out.println("-------------用户注册信息----------");
		System.out.println("姓名:"+username);
		System.out.println("密码:"+password);
		if("abc".equals(username) && "123".equals(password)){
			msg = "用户登录成功";
		}else{
			msg = "用户登录失败";
		}
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
