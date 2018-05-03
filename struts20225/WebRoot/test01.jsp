<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- struts2的标签库引入 -->
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title>结果显示</title>
  </head>
  <body>
  	<!-- 取出错误堆栈信息 -->
  	<s:fielderror/>
  	<center><h1>Test01结果显示</h1></center>
  	application:${sapplicationScopt.app };<br>
  	session:${sessionScopt.session };<br>
  	request:${requestScopt.request};<br>
  </body>
</html>
