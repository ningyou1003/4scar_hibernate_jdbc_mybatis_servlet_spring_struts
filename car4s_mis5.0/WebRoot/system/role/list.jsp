<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>角色列表</title>
<script type="text/javascript">
  var dialog;
  //把url对应页面在弹出窗口中打开
  function showDialog(url,title){
     dialog = $.dialog({
        title:title,
     	width: '400px',
    	height:'420px',
    	content: 'url:'+url
	 });
  }
  //关闭
  function closeDialog(result){
    dialog.close();
    $("#result_show").html(result);	
  }
</script>
</head>
<body>
    <div class="alert alert-info">
                   当前位置<b class="tip"></b>系统管理<b class="tip"></b>角色列表
    </div>
    <form id="form01" action="${_cxt}/role_list.do"  method="post">
    <!-- 隐藏域 用于判断是不是提交表单-->
    <s:hidden name="commit" value="true"/>
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">
                                         查询条件 <i class="tip-up"></i>
                 <div style="color:red;float: right;">
	             ${msg}<span id="result_show"></span>
	             </div>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td align="right">角色名称:</td>
              <td align="left">
                 <s:textfield name="role.name" cssClass="ipt"/>
              </td>
              <td align="right"></td>
              <td align="left"></td>
            </tr>
            <tr>
              <td colspan="2" ></td>
              <td colspan="2" align="center">
                   <input class="btn" type="submit" value="查询" />
                   <input class="btn" type="button" onclick="clean();" value="清空" />
                   <input class="btn" type="button" value="新增" 
                          onclick="location.href='${_cxt}/system/role/add.jsp'"/>
                </td>
            </tr>
          </tbody>
      </table>
    <table class="tb">
        <thead>
	     <tr>
	        <th width="6%">记录ID</th>
	        <th width="8%">角色名称</th>
	        <th width="8%">创建日期</th>
	        <th width="8%">操作</th>
	     </tr>
        </thead>
        <tbody>
            <%--循环列表 --%>
		    <s:iterator  value="#request.list" var="v" status="status">
		     <!-- 删除链接变量 -->
		     <s:url id ="delete" action="role_delete">
		        <s:param name="role.id">${v.id}</s:param>
		     </s:url>
		     <!-- 更新链接变量 -->
		     <s:url id ="update" action="role_update">
		        <s:param name="role.id">${v.id}</s:param>
		     </s:url>
		     <!-- 弹出菜单树链接 -->
		     <s:url id="menuTree" action="menu_menuTree">
		        <s:param name="roleId">${v.id}</s:param>
		     </s:url>	     
		     <tr <s:if test="#status.even">class='even'</s:if>>
		        <td>${v.id}</td>
		        <td>${v.name}</td>
		        <td>
		            <s:date name="#v.createDate" format="yyyy-MM-dd"/>
		        </td>
		        <td>
		          <s:a href="%{delete}" cssClass="btn btn-mini btn-danger"
		            onclick="return confirm('你确定删除？');">删除</s:a> 
	              <s:a href="%{update}" 
	                 cssClass="btn btn-mini btn-primary">更新</s:a> 
	              <s:a href="#" onclick="showDialog('%{menuTree}','正在为角色分配权限')" 
	                 cssClass="btn btn-mini btn-primary">权限分配</s:a> 
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

