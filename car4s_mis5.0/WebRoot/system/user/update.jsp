<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>用户更新页面</title>
</head>
<body>
<!--     <div class="alert alert-info">
                   当前位置<b class="tip"></b>用户管理<b class="tip"></b>用户更新
    </div> -->
    <form action="${_cxt}/user_update.do" method="post">
     <!-- 隐藏域 用于判断是不是提交表单-->
     <s:hidden name="commit" value="true"/>
     <%-- 隐藏域 待更新记录的ID--%>
     <s:hidden name="user.id"/>
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">
                                         用户更新<i class="tip-up"></i>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td align="right">用户姓名:</td>
              <td align="left">
                 <s:textfield name="user.name" cssClass="ipt"/>
              </td>
              <td align="right"></td>
              <td align="left"></td>
            </tr>
            <tr>
              <td align="right">用户账号:</td>
              <td align="left">
                 <s:textfield name="user.username" cssClass="ipt"/>
              </td>
              <td align="right">性别:</td>
              <td align="left">
                 <s:select list="#application.SYS_SEX" name="user.sex" cssClass="ipt"/>
              </td>

            </tr>
            <tr>
              <td align="right">出生日期:</td>
              <td align="left">
                 <input name="user.birthday" class="ipt"
                    value='<s:date name="user.birthday" format="yyyy-MM-dd"/>'
                 />
              </td>
              <td align="right">入职日期:</td>
              <td align="left">
                 <input name="user.entryDate" class="ipt"
                  value='<s:date name="user.entryDate" format="yyyy-MM-dd"/>'
                 />
              </td>
            </tr>
            <tr>
              <td align="right">所属部门:</td>
              <td align="left">
                 <s:select list="#application.deptMap" name="user.deptId" cssClass="ipt"/>
              </td>
              <td align="right">用户角色:</td>
              <td align="left">
                 <s:select list="#application.roleMap" name="user.roleId" cssClass="ipt"/>
              </td>
            </tr>
            <tr>
              <td align="right">登录状态:</td>
              <td align="left">
                 <s:select list="#application.SYS_LOGIN_FLAG" 
                           name="user.loginFlag" cssClass="ipt"/>
              </td>
              <td align="right">申请状态:</td>
              <td align="left">
                 <s:select list="#application.APPLY_STATE" 
                           name="user.applyState" cssClass="ipt"/>
              </td>
            </tr>
              <tr>
                <td colspan="2"></td>
                <td colspan="2" align="center">
                   <input class="btn" type="submit" value="更新" />
                   <input class="btn" type="reset" value="清空" />
                   <input class="btn" type="button" 
                          value="返回" onclick="history.back()"/>
                </td>
            </tr>
          </tbody>
      </table>
      </form>
</body>
</html>

