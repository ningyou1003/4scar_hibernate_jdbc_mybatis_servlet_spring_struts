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
    <script type="text/javascript" src="${_js}/jquery.spritely-0.6.js"></script>
    <script type="text/javascript" src="${_js}/chur.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${_css}/login.css" />
    <script type="text/javascript">
         $(function () {
            $('#clouds').pan({ fps: 20, speed: 0.7, dir: 'right', depth: 10 });
        }); 
        //输入校验
        function input_check() {
           if ($('#uid').val().trim() == "" 
            || $('#pwd').val().trim() == "") { 
              $('.tip').html('用户名或密码不可为空！'); 
              return false;
           }else {
              return true;
           }
        }
    </script>
</head>
<body>
    <div id="clouds" class="stage"></div>
    <div class="loginmain"></div>
    <div class="row-fluid">
      <form action="${_cxt}/sys_login.do" method="post" onsubmit="return input_check();">
        <!-- 隐藏域 -->
        <s:hidden name="commit" value="true"/>
        <h1>${_title}</h1>
        <center><span style="color:red">${msg}${param.msg}</span></center>
        <p>
            <label>用&nbsp;户&nbsp;名：<s:textfield name="user.username" id="uid" /></label>
        </p>
        <p>
            <label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<s:password name="user.password" id="pwd" /></label>
        </p>
        <%-- 
        <p class="pcode">
            <label>&nbsp;&nbsp;&nbsp;效&nbsp;验&nbsp;码：
            <input type="text" id="code" maxlength="5" class="code" value="e5g88" />
            <img src="${_images}/code.gif" alt="" class="imgcode" />
            <a href="#">下一张</a></label>
        </p>
         --%>
        <p class="tip">&nbsp;</p>
        <hr/>
        <input type="submit" value="登 录 " class="btn btn-primary btn-large login" />
        &nbsp;&nbsp;&nbsp;
        <input type="reset" value="重 置 " class="btn btn-large" />
        &nbsp;&nbsp;&nbsp;
        <input type="button" value="注 册 " class="btn btn-large" 
               onclick="location.href='${_cxt}/common/register.jsp'"/>
      </form>
    </div>
</body>
</html>
