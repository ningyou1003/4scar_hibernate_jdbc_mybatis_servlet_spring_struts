<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>菜单更新页面</title>
</head>
<body>
    <form action="${_cxt}/menu_update.do" method="post">
     <!-- 隐藏域 用于判断是不是提交表单-->
     <s:hidden name="commit" value="true"/>
     <%-- 隐藏域 待更新记录的ID--%>
     <s:hidden name="menu.id"/>
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">
                                     菜单更新<i class="tip-up"></i>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td align="right">菜单名称:</td>
              <td align="left">
                 <s:textfield name="menu.name" cssClass="ipt"/>
              </td>
              <td align="right">URL:</td>
              <td align="left">
                 <s:textfield name="menu.url" cssClass="ipt"/>
              </td>
            </tr>
            <tr>
              <td align="right">父菜单:</td>
              <td align="left">
                 <s:select list="#application.menuMap" 
                           name="menu.parentId" cssClass="ipt"
                           headerKey="0" headerValue="--请选择--"/>
              </td>
              <td align="right">菜单级别:</td>
              <td align="left">
                 <s:select list="#application.SYS_MENU_LEVEL" 
                           name="menu.menuLevel" cssClass="ipt"
                           headerKey="0" headerValue="--请选择--"/>
              </td>
            </tr>            
            <tr>
               <td align="right">可用标签:</td>
               <td align="left">
                 <s:select list="#application.SYS_USE_FLAG" 
                           name="menu.useFlag" cssClass="ipt"
                           headerKey="0" headerValue="--请选择--"/>  
               </td> 
                <td colspan="2" align="center">
                   <input class="btn" type="submit" value="更新" />
                   <input class="btn" type="reset" value="清空" />
                   <input class="btn" type="button" value="返回" onclick="history.back()"/>
                </td>
            </tr>
          </tbody>
      </table>
      </form>
</body>
</html>

