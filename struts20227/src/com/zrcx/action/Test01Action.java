package com.zrcx.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.zrcx.entity.User;

public class Test01Action extends ActionSupport {
	
	private static final long serialVersionUID = 1L;//版本号，
	private String name;
	private List<User> list;
	
	
	@Override
	public String execute() throws Exception {
		System.out.println("------test01------");
		this.name="<h1>姓名</h1>";
		list = new ArrayList<User>();
		list.add(new User("小二",18));
		list.add(new User("小三",58));
		list.add(new User("小四",8));
		
		return "test01";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
