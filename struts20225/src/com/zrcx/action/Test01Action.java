package com.zrcx.action;

import javax.servlet.ServletContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Test01Action extends ActionSupport {
	
	private static final long serialVersionUID = 1L;//版本号，
	private String name;
	
	
	@Override
	public String execute() throws Exception {
		System.out.println("------test01登录------");
		ActionContext ac = ActionContext.getContext();
		ac.getApplication().put("app", "app属性");
		ac.getSession().put("session", "session属性");
		ac.put("request", "request属性");//request
		
		//在struts2里获取servlet内置对象
//		ServletContext
		return "test01";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
