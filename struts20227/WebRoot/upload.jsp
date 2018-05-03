<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- struts2的标签库引入 -->
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title>文件上传示</title>
  </head>
  <body>
  	<h2>文件上传</h2>
  	<s:form namespace="/" action="uploadAction" enctype="multipart/form-data" method="post">
  	<s:textfield label="姓名" name="name"/>
  	<s:file label="头像" name="image"/>
  	<s:submit value="上传"/>
  	</s:form>
  	

  	
  </body>
</html>









