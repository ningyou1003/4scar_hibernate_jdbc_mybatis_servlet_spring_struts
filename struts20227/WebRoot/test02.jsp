<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- struts2的标签库引入 -->
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title>结果显示</title>
  </head>
  <body>
  	<h2>结果显示页面（Test02）</h2>
  	<s:url var="url1" action="test01Action" namespace="/">
  		<s:param name="username" value="%{'admin'}"/>
  		<s:param name="age" value="%{'20'}"/>
  	</s:url>
  	<h2>url:<s:property value="url1"/></h2>
  	<a href='<s:property value="url1"/>'>链接</a>
  	<hr/>
  	<h2>表单元素</h2>
  	<s:form namespace="/" action="test02Action" method="post" theme="xhtml">
  		<s:textfield label="姓名" name="name" tabindex="2"/> <!-- tabindex="2"按tab键切换 -->
  		<s:textfield label="用户名" name="username" tabindex="1" readonly="true"/>
  		<s:password label="密码" name="password" showPassword="false"/><!-- showPassword="false"密码是否回显，提交了之后显示空 -->
  		<s:hidden name="id" value="100"></s:hidden>
  		<s:textarea cols="20" rows="4" label="个人简介" value="123"/> 
  		<%-- <s:checkbox label="勾选" name="n1" key="1" value="唱歌"></s:checkbox> --%>
  		<%-- 在OGNL中#{1:'篮球',2:'足球',3:'旅行'}表示一个map集合 --%>
  		<s:checkboxlist label="爱好" name="hobby" list="#{1:'篮球',2:'足球',3:'旅行'}"
  						listKey="key" listValue="value" value="{1,3}"/>
  						<%-- listKey="value" listValue="key"/> --%>
  		<%-- 用数组作为数据源，value不写，key和value都是篮球 --%>
  		<%-- <s:checkboxlist label="爱好" name="hobby" list="{'篮球','足球','旅行'}"/>
  			--%> 
  		<s:checkboxlist label="爱好" name="hobby" list="list" listKey="age" listValue="name"/> 
  		<s:radio label="最喜欢的运动" name="mosthobby" list="#{1:'篮球',2:'足球',3:'旅行'}"></s:radio> 						
  		<s:radio label="最喜欢的运动" name="mosthobby" list="list" listKey="age" listValue="name" 
  		value="3"/>
  		<s:select label="最喜欢的运动" name="hobby2" emptyOption="false"
  		headerKey="0" headerValue="--请选择--"
  		list="#{1:'篮球',2:'足球',3:'旅行'}">
  		</s:select>					
  		<s:submit type="input" value="提交一"/>  <!-- struts2标签有回显功能 -->
  		<s:submit type="button" value="提交二"/>  
  		<s:submit type="image" value="提交三"/>  
  		<s:reset type="input">重置</s:reset>
  	</s:form>
  </body>
</html>









