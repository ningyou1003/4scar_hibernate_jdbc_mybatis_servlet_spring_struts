<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- struts2的标签库引入 -->
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title>结果显示</title>
  </head>
  <body>
  	<h2>结果显示页面（Test03）</h2>
  	<h3>访问静态方法:<s:property value="@com.zrcx.action.Test@getName()"/></h3>
  	<h3>访问静态属性:<s:property value="@com.zrcx.action.Test@STR" /></h3>
  	<h3>访问Math类静态常量:<s:property value="@@PI" /></h3>
  	<h3>ONGL语句:<s:property value="#price=100,#discount=0.6,#price*#discount" /></h3>
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  </body>
</html>









