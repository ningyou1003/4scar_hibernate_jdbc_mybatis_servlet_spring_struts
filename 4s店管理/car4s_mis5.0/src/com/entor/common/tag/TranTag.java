package com.entor.common.tag;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
/**
 * 字典翻译标签处理类
 * @author ZHQL
 */
public class TranTag extends BodyTagSupport {
	
	private static final long serialVersionUID = 1L;
	private String value;//要翻译的key
	//数据源(key-选项key,value-选项内容)
	private Map<String,String> data;
	
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
		  if(data != null && data.get(value)!= null){
			  out.print(data.get(value));
		  }else{
			  out.print("--");//翻译不了
		  }
		  out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return EVAL_PAGE;
	}
	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
}











