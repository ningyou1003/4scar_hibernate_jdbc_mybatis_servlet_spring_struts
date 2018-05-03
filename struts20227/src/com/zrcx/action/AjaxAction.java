package com.zrcx.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class AjaxAction extends ActionSupport {
	private static final long serialVersionUID = 1L;//版本号，

	private String jsonTest;//json字符串

	@Override
	public String execute() throws Exception {
		
		System.out.println("------请求处理------");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		jsonTest = sdf.format(new Date());
		return SUCCESS;
	}

	public String getJsonTest() {
		return jsonTest;
	}

	public void setJsonTest(String jsonTest) {
		this.jsonTest = jsonTest;
	}



}
