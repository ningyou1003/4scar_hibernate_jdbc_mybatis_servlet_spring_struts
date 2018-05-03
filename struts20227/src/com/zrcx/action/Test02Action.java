package com.zrcx.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.zrcx.entity.User;

public class Test02Action extends ActionSupport {
	
	private static final long serialVersionUID = 1L;//版本号，
	private String name;
	private String username;
	private String password;
	private int id;
	private List<User> list;
	
	@Override
	public String execute() throws Exception {
		System.out.println("------test02------");
		System.out.println("姓名:"+name);
		System.out.println("用户名:"+username);
		System.out.println("密码:"+password);
		System.out.println("ID:"+id);
		list = new ArrayList<User>();
		list.add(new User("唱歌",1));
		list.add(new User("跳舞",2));
		list.add(new User("游泳",3));
		return "test02";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}

	
}
