<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>用户列表</title>
</head>
<body>
    <div class="alert alert-info">
                   当前位置<b class="tip"></b>用户管理<b class="tip"></b>用户列表
    </div>
    <form id="form01" action="${_cxt}/user_list.do"  method="post">
    <!-- 隐藏域 用于判断是不是提交表单-->
    <s:hidden name="commit" value="true"/>
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">
                                         查询条件 <i class="tip-up"></i>
                 <div style="color:red;float: right;">
	             ${msg}
	             </div>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td align="right">用户姓名:</td>
              <td align="left">
                 <s:textfield name="user.name" cssClass="ipt"/>
              </td>
              <td align="right">用户账号:</td>
              <td align="left">
                 <s:textfield name="user.username" cssClass="ipt"/>
              </td>
            </tr>
            <tr>
              <td align="right">所属部门:</td>
              <td align="left">
                 <s:select list="#application.deptMap" 
                            name="user.deptId" cssClass="ipt"
                            headerKey="0" headerValue="--请选择--"
                 />
              </td>
              <td align="right">登录状态:</td>
              <td align="left">
                 <s:select list="#application.SYS_LOGIN_FLAG" 
                           name="user.loginFlag" cssClass="ipt"
                           headerKey="0" headerValue="--请选择--"
                 />
              </td>
            </tr>
            <tr>
                <td colspan="2"></td>
                <td colspan="2" align="center">
                   <input class="btn" type="submit" value="查询" />
                   <input class="btn" type="button" onclick="clean();" value="清空" />
                   <s:if test="'user_add.do' in #session.priv">
                     <input class="btn" type="button" value="新增" 
                          onclick="location.href='${_cxt}/system/user/add.jsp'"/>
                   </s:if>
                </td>
            </tr>
          </tbody>
      </table>
    <table class="tb">
        <thead>
	     <tr>
	        <th width="6%">记录ID</th>
	        <th width="8%">姓名</th>
	        <th width="6%">性别</th>
	        <th width="6%">所属部门</th>
	        <th width="8%">出生日期</th>
	        <th width="8%">入职日期</th>
	        <th width="6%">用户名</th>
	        <th width="6%">登录状态</th>
	        <th width="6%">申请状态</th>
	        <th width="8%">角色</th>
	        <th width="8%">创建日期</th>
	        <th width="8%">操作</th>
	     </tr>
        </thead>
        <tbody>
            <%--循环列表 --%>
		    <s:iterator  value="#request.list" var="v" status="status">
		     <!-- 删除链接变量 -->
		     <s:url id ="delete" action="user_delete">
		        <s:param name="user.id">${v.id}</s:param>
		     </s:url>
		     <!-- 更新链接变量 -->
		     <s:url id ="update" action="user_update">
		        <s:param name="user.id">${v.id}</s:param>
		     </s:url>	     
		     <tr <s:if test="#status.even">class='even'</s:if>>
		        <td>${v.id}</td>
		        <td>${v.name}</td>
		        <td><mt:tran data="${application.SYS_SEX}" value="${v.sex}"/></td>
		        <td><mt:tran data="${application.deptMap}" value="${v.deptId}"/></td>
		        <td>
		           <s:date name="#v.birthday" format="yyyy-MM-dd"/>
		        </td>
		        <td>
		           <s:date name="#v.entryDate" format="yyyy-MM-dd"/>
		        </td>
		        <td>${v.username}</td>
		        <td><mt:tran data="${application.SYS_LOGIN_FLAG}" value="${v.loginFlag}"/></td>
		        <td><mt:tran data="${application.APPLY_STATE}" value="${v.loginFlag}"/></td>
		        <td><mt:tran data="${application.roleMap}" value="${v.roleId}"/></td>
		        <td>
		            <s:date name="#v.createDate" format="yyyy-MM-dd"/>
		        </td>
		        <td>
		         <s:if test="'user_delete.do' in #session.priv">
		          <s:a href="%{delete}" cssClass="btn btn-mini btn-danger"
		            onclick="return confirm('你确定删除？');">删除</s:a> 
		         </s:if>
		         <s:else><del>删除</del></s:else>
		         <s:if test="'user_update.do' in #session.priv">
	              <s:a href="%{update}" cssClass="btn btn-mini btn-primary">更新</s:a> 
	             </s:if>
	             <s:else><del>更新</del></s:else> 
		        </td>
	     </tr>
         </s:iterator>
        </tbody>
    </table>
    <center>
    <%@ include file="/common/pager.jsp"%>
    </center>
    </form>
</body>
</html>

