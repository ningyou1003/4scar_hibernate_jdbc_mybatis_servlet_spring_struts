package com.entor.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.entor.common.Page;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 基类
 * @author ZHQL
 */
public class BaseAction extends ActionSupport implements
    ServletRequestAware,
    ServletResponseAware,
    ServletContextAware{
	private static final long serialVersionUID = 1L;
	ServletContext sc;
	HttpServletRequest request;
	HttpServletResponse response;
	//提示信息
	private String msg;
	//判断是否提交表单
	private boolean commit;
	//页面对象
	private Page page;
	//json格式字符串
	private String jsonText;
	
	@Override
	public void setServletContext(ServletContext sc) {
		this.sc = sc;		
	}
	@Override
	public void setServletResponse(HttpServletResponse resp) {
		this.response = resp;		
	}
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isCommit() {
		return commit;
	}
	public void setCommit(boolean commit) {
		this.commit = commit;
	}
	public Page getPage() {
		//初次访问时page为null
		if(null == page){
			page = new Page();
		}
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getJsonText() {
		return jsonText;
	}
	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}
}











