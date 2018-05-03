<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>菜单列表</title>
</head>
<body>
    <div class="alert alert-info">
                   当前位置<b class="tip"></b>系统管理<b class="tip"></b>菜单列表
    </div>
    <form id="fm1" action="${_cxt}/menu_list.do"  method="post">
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
              <td align="right">菜单名称:</td>
              <td align="left">
                 <s:textfield name="menu.name" cssClass="ipt"/>
              </td>
              <td align="right">可用标签:</td>
              <td align="left">
                 <s:select list="#application.SYS_USE_FLAG" 
                           name="menu.useFlag" cssClass="ipt"
                           headerKey="0" headerValue="--请选择--"/>
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
              <td colspan="2"></td>
              <td align="right"></td>
              <td colspan="1" align="left">
                   <input class="btn" type="submit" value="查询" onclick="$('#currentPage').val(1);"/>
                   <input class="btn" type="button" onclick="clean();" value="清空" />
                   <s:if test="'menu_add.do' in #session.priv">
                     <input class="btn" type="button" value="新增" 
                          onclick="location.href='${_cxt}/system/menu/add.jsp'"/>
                   </s:if>
                </td>
            </tr>
          </tbody>
      </table>
    <table class="tb">
        <thead>
	     <tr>
	        <th width="6%">记录ID</th>
	        <th width="8%">菜单名称</th>
	        <th width="6%">URL</th>
	        <th width="6%">父菜单</th>
	        <th width="6%">菜单级别</th>
	        <th width="6%">可用标志</th>
	        <th width="8%">创建日期</th>
	        <th width="8%">操作</th>
	     </tr>
        </thead>
        <tbody>
            <%--循环列表 --%>
		    <s:iterator  value="#request.list" var="v" status="status">
		     <!-- 删除链接变量 -->
		     <s:url id ="delete" action="menu_delete">
		        <s:param name="menu.id">${v.id}</s:param>
		     </s:url>
		     <!-- 更新链接变量 -->
		     <s:url id ="update" action="menu_update">
		        <s:param name="menu.id">${v.id}</s:param>
		     </s:url>	     
		     <tr <s:if test="#status.even">class='even'</s:if>>
		        <td>${v.id}</td>
		        <td>${v.name}</td>
		        <td>${v.url}</td>
		        <td>
		          <mt:tran data="${application.menuMap}" value="${v.parentId}"/>
		        </td>
		        <td>
		          <mt:tran data="${application.SYS_MENU_LEVEL}" value="${v.menuLevel}"/>
		        </td>
		        <td>
		          <mt:tran data="${application.SYS_USE_FLAG}" value="${v.useFlag}"/>
		        </td>
		        <td>
		            <s:date name="#v.createDate" format="yyyy-MM-dd"/>
		        </td>
		        <td>
		          <s:if test="'menu_delete.do' in #session.priv">
		           <s:a href="%{delete}" cssClass="btn btn-mini btn-danger"
		            onclick="return confirm('你确定删除？');">删除</s:a> 
	              </s:if>
	              <s:else><del>删除</del></s:else>
	              <s:if test="'menu_update.do' in #session.priv">
	              <s:a href="%{update}" 
	                 cssClass="btn btn-mini btn-primary">更新</s:a>
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

