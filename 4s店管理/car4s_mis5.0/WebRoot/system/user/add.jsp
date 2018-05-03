<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>用户新增页面</title>
</head>
<body>
    <form action="${_cxt}/user_add.do" method="post" onsubmit="return tt.validate();">
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">
                                         用户新增 <i class="tip-up"></i>
              </th>
            </tr>
          </thead>
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
                 <s:select list="#application.SYS_SEX" 
                 name="user.sex" cssClass="ipt"
                 headerKey=""  headerValue="--请选择--"
                 />
              </td>
            </tr>
            <tr>

              <td align="right">出生日期:</td>
              <td align="left">
                 <s:textfield name="user.birthday" cssClass="ipt"/>
              </td>              
              <td align="right">入职日期:</td>
              <td align="left">
                 <s:textfield name="user.entryDate" cssClass="ipt"/>
              </td>
            </tr>
<%--             <tr>
              <td align="right">密码:</td>
              <td align="left">
                 <s:password name="user.password" cssClass="ipt"/>
              </td>
              <td align="right">确认密码:</td>
              <td align="left">
                 <s:password name="password2" cssClass="ipt"/>
              </td>
            </tr> --%>
            <tr>
              <td align="right">所属部门:</td>
              <td align="left">
                 <s:select list="#application.deptMap" 
                   name="user.deptId" cssClass="ipt"
                   headerKey=""  headerValue="--请选择--"
                   />
              </td>
              <td align="right">用户角色:</td>
              <td align="left">
                 <s:select list="#application.roleMap" 
                 name="user.roleId" cssClass="ipt"
                 headerKey=""  headerValue="--请选择--"
                 />
              </td>
            </tr>
            <tr>
              <td align="right">登录状态:</td>
              <td align="left">
                 <s:select list="#application.SYS_LOGIN_FLAG" 
                           name="user.loginFlag" cssClass="ipt"
                           headerKey=""  headerValue="--请选择--"
                           />
              </td>
              <td align="right"><!-- 申请状态: --></td>
              <td align="left">
               <%--   <s:select list="#application.APPLY_STATE" 
                           name="user.applyState" cssClass="ipt"
                           headerKey=""  headerValue="--请选择--"
                           /> --%>
              </td>
            </tr>
              <tr>
                <td colspan="2"></td>
                <td colspan="2" align="center">
                   <input class="btn" type="submit" value="添加" />
                   <input class="btn" type="reset" value="清空" />
                   <input class="btn" type="button" value="返回" onclick="history.back()"/>
                </td>
            </tr>
      </table>
      </form>
 <script type="text/javascript">
	tt.vf.req.add("user.name","user.username","user.sex"
	,"user.loginFlag","user.birthday","user.entryDate","user.password","password2"
	,"user.deptId","user.roleId","user.applyState");
	new tt.DV().set("yyyy-MM-dd").add("user.birthday"); 
</script>
</body>
</html>

