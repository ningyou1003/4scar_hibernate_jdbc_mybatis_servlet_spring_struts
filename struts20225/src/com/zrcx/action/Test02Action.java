package com.zrcx.action;

import com.opensymphony.xwork2.ActionSupport;

public class Test02Action extends ActionSupport {
	
	private static final long serialVersionUID = 1L;//版本号，
	
	public String list() throws Exception {
		System.out.println("------list------");
		return "test02";
	}
	public String add() throws Exception {
		System.out.println("------add------");
		return "test02";
	}
}
