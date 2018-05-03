package com.entor.common.tag;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
/**
 * 下拉框自定义标签处理类
 * @author ZHQL
 */
public class SelectTag extends BodyTagSupport {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String value;//默认选择项key
	private String cssClass;//样式类
	private String onchange;//变更事件
	private String header;//是否显示首选项(格式:  0:--请选择--)
	//数据源(key-选项key,value-选项内容)
	private Map<String,String> data;
	
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
		  //标签开始和属性
		  out.println("<select "+ 
		  (null!=id && id!="" ? " id='"+ id +"'":"")+
		  (null!=name && name!="" ? " name='"+ name +"'":"")+
	(null!=cssClass&&cssClass!=""?"class='"+cssClass+"'":"")+
	(null!=onchange&&onchange!=""?"onchange='"+onchange+"'":"")
		  +" >");
		  //首选项(格式 key:value)
		  if(null!=header&&!header.trim().equals("")){
			  String[] head = header.split(":");
			  out.println("<option value='"+head[0]+"'>");
			  out.print(head[1] + "</option>");
		  }
			// 循环所有选项
			if (data != null && data.size() > 0) {// 非空
				for (Map.Entry<String, String> v : data.entrySet()) {
					if (null != value && value.equals(v.getKey())) {
						out.write("<option selected='selected' value='"
								+ v.getKey() + "'>" + v.getValue()
								+ "</option>");
					} else {
						out.write("<option value='" + v.getKey() + "'>"
								+ v.getValue() + "</option>");
					}
				}
			}
			out.println("</select>");
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getOnchange() {
		return onchange;
	}
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
}











