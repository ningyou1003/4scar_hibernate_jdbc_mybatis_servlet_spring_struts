<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${_title}</title>
    <link rel="stylesheet" type="text/css" href="${_css}/base.css" />
    <link rel="stylesheet" type="text/css" href="${_css}/admin-all.css" />
    <link rel="stylesheet" type="text/css" href="${_css}/bootstrap.min.css" />
    <script type="text/javascript" src="${_plugins}/jquery-1.7.2.js"></script>
   <%--  <script type="text/javascript" src="${_js}/jquery.spritely-0.6.js"></script> --%>
    <%-- <script type="text/javascript" src="${_js}/chur.min.js"></script> --%>
    <link rel="stylesheet" type="text/css" href="${_css}/login.css" />
</head>
<body>
    <div id="clouds" class="stage"></div>
    <div class="loginmain"></div>
    <div class="row-fluid">
      <form action="${_cxt}/sys_register.do" method="post">
        <!-- 隐藏域 -->
        <s:hidden name="commit" value="true"/>
        <h2>用户注册</h2>
        <table align="center" width="80%">
          <tr>
            <td align="right">姓&nbsp;&nbsp;名:</td>
            <td align="left"><s:password name="user.name" id="pwd" /></td>
          </tr>
          <tr>
            <td align="right">出生日期:</td>
            <td align="left"><s:textfield name="user.name" id="pwd" /></td>
          </tr>
          <tr>
            <td align="right">出生日期:</td>
            <td align="left"><s:textfield name="user.name" id="pwd" /></td>
          </tr>
          <tr>
            <td align="right">出生日期:</td>
            <td align="left"><s:textfield name="user.name" id="pwd" /></td>
          </tr>
          <tr>
            <td align="right">重输密码:</td>
            <td align="left"><s:password name="user.name" id="pwd" /></td>
          </tr>
        </table>
        <p class="tip">&nbsp;</p>
        <hr/>
        <input type="submit" value="注 册" class="btn btn-primary btn-large login" />
        &nbsp;&nbsp;&nbsp;
        <input type="button" value="取 消 " class="btn btn-large" onclick="history.back();"/>
      </form>
    </div>
</body>
</html>
