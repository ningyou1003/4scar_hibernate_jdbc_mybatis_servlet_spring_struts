package com.zrcx.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;//版本号，
	private String name;
	private Date birthday;
	private int sex;
	
	
	@Override
	public String execute() throws Exception {
		System.out.println("------页面登录------");
		System.out.println("name"+name);
		return "index";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	
	

	
}
