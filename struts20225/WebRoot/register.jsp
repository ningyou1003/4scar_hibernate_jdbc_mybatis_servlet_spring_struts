<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- struts2的标签库引入 -->
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统注册页面</title>
</head>
<body>
	    <s:form namespace="/" action="registerAction" >
		<s:textfield label="姓名" name="user.name"/>
		<s:password label="生日" name="user.birthday"/>
		<s:select list="#{1:'男',2:'女'}" name="user.sex"/>
		<s:submit value="注册"/>
	</s:form>
</body>
</html>