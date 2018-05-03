<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>异常信息显示页面</title>
    <link rel="stylesheet" type="text/css" 
          href="${_css}/formui.css" />
    <link rel="stylesheet" type="text/css" 
          href="${_css}/base.css"/>
</head>
<body>
       <center>出现系统异常，请联系系统管理员处理！</center>
       <center>${exception.message}</center>
       <center><pre>${exceptionStack}</pre></center>
</body>
</html>

