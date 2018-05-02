<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.emindsoft.zsj.base.attachment.model.Attachment"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'initPath.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"><%@ page import="com.jfinal.plugin.activerecord.Record"%>
<%@ page import="java.util.List"%>
<%@ page import="com.jfinal.plugin.activerecord.Db"%>
<%@ page import="com.emindsoft.zsj.system.model.Role"%>
<%@ page import="com.emindsoft.zsj.system.model.User"%>
<%@ page import="com.emindsoft.zsj.system.model.RoleUser"%>
<%@ page import="com.emindsoft.zsj.system.model.RolePower"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.jfinal.plugin.activerecord.DbKit"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
	Connection conn = DbKit.getConfig().getDataSource().getConnection();
	DbKit.getConfig().setThreadLocalConnection(conn);
	conn.setAutoCommit(false);//自动提交变成false

	List<Record> attList = Db.find("SELECT keyid,path,name,relateType FROM base_attachment");
	try{
		for(Record record:attList){
	/* String pathUrl = record.getStr("path");
	if (StringUtils.isEmpty(pathUrl)) {
		String keyid = record.getStr("keyid");
		String name = record.getStr("name");
		String[] names = name.split("\\.");
		Attachment att = new Attachment();
		String path = "";
		if (names.length > 0)
			if ("kindEditorAttach".equals(record
					.getStr("relateType"))) {
			}
		att.set("keyid", keyid).set("path", "/" + keyid)
				.saveOrUpdate();
	} */
			String keyid = record.getStr("keyid");
			String name = record.getStr("name");
			String[] names = name.split("\\.");
			Attachment att = new Attachment();
			String pathUrl = "";
			if (names.length > 0){
				if ("kindEditorAttach".equals(record.getStr("relateType"))) {
					pathUrl = "ke_upload/"+record.getStr("path");
				} else {
					pathUrl += "upload/"+keyid;
				}
				att.set("keyid", keyid).set("path", pathUrl)
					.saveOrUpdate();
			}
				
		}
		conn.commit();
		System.out.println("finish");
	} catch (Exception e) {
		e.printStackTrace();
		conn.rollback();
	}
%>
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
