<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Stuts2拦截器错误信息显示页面(开发时使用)</title>
    <link rel="stylesheet" type="text/css" href="${_css}/formui.css" />
    <link rel="stylesheet" type="text/css" href="${_css}/base.css"/>
</head>
<body>
       <center>系统拦截器错误信息！</center>
       <!-- 字段转换错误 -->
       <center><pre><s:fielderror/></pre></center>
       <!-- action错误 -->
       <center><pre><s:actionerror/></pre></center>
</body>
</html>

