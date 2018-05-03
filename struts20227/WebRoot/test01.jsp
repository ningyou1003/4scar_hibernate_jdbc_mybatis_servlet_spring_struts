<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- struts2的标签库引入 -->
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结果显示页面</title>
</head>
<body>
  <h2>结果显示页面(struts2)</h2>
  <!-- EL表达式可以读取action的属性 -->
  <h2>EL表达式:${name}</h2>
  <!-- OGNL表达式结合struts2标签可以读取action的属性,会把value当成OGNL执行 -->
  <h2>OGNL表达式(到值栈去找name):<s:property value="name" default="默认值" escape="true"/></h2>
  <h2>OGNL表达式(把name当字符串打印):<s:property value="%{'name'}"/></h2>
  <hr/>
  <!-- 在页面定义变量 -->
  <s:set var="v1" value="%{'王二小'}"></s:set>
  <s:set var="v2" value="%{'王三小'}" scope="request"></s:set>
  <!-- 默认是从valueStack读数据 -->
  <h2>v1:<s:property value="v1"/></h2>
  <!-- #表示容器的根 -->
  <h2>v2:<s:property value="#request.v2"/></h2>
  <!-- 从小范围到大范围page>request>session>application查找 -->
  <h2>v2:<s:property value="#attr.v2"/></h2>
  <hr/>
  <!-- 访问action -->
  <%-- <s:action name="test02Action" namespace="/" executeResult="true"/> --%>
  <hr>
  <!-- 定义变量,{'南宁','桂林','北海'}在ognl用表示一个数组 -->
  <s:set var="v3" value="{'南宁','桂林','北海'}"></s:set>
  <s:iterator value="v3" var="v" status="sta"><!-- v默认放在valueStack栈顶 -->
     <%-- <s:property value="v"/><br> --%>
     <%-- <s:property/> --%>
     ${v}<!-- 可以用EL读取 -->
     -count:<s:property value="#sta.count"/>
     -index:<s:property value="#sta.index"/>
     -first:<s:property value="#sta.first"/>
     -even:<s:property value="#sta.even"/>    <%-- 是否是奇数，偶数，第一行 --%>
     <br>
  </s:iterator>
  <!-- 集合里装的是对象,会放在actionContext下，读取要加# -->
  <s:iterator value="list" var="v" status="sta">
     <s:property value="#v.name"/>-<s:property value="#v.age"/>
       <s:if test="#v.age < 18">少年</s:if>
       <s:elseif test="#v.age <60">成年</s:elseif>
       <s:else>老年</s:else>
     <br>
  </s:iterator>
</body>
</html>



