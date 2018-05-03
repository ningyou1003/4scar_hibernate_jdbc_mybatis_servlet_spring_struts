<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>客户列表</title>
</head>
<body>
    <div class="alert alert-info">
                   当前位置<b class="tip"></b>系统管理<b class="tip"></b>客户列表
    </div>
    <form id="fm1" action="${_cxt}/customer_list.do"  method="post">
    <!-- 隐藏域 用于判断是不是提交表单-->
    <s:hidden name="commit" value="true"/>
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">
                                         查询条件 <i class="tip-up"></i>
                 <div style="color:red;float: right;">${message}</div>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td align="right">客户名称:</td>
              <td align="left">
                 <s:textfield name="customer.name" cssClass="ipt"/>
              </td>
              <td align="right">性别:</td>
              <td align="left">
                 <s:select name="customer.sex"
                       list="#application.SYS_SEX" cssClass="ipt"
                       headerKey="0" headerValue="--请选择--"/>
              </td>
            </tr>
            <tr>
              <td align="right">身份证:</td>
              <td align="left">
                 <s:textfield name="customer.idNo" cssClass="ipt"/>
              </td>
              <td align="right"></td>
              <td colspan="1" align="left">
                   <input class="btn" type="submit" value="查询" />
                   <input class="btn" type="button" onclick="clean();" value="清空" />
                   <input class="btn" type="button" value="新增" 
                          onclick="location.href='${_cxt}/customer/customer/add.jsp'"/>
                </td>
            </tr>
          </tbody>
      </table>
    <table class="tb">
        <thead>
	     <tr>
	        <th width="6%">记录ID</th>
	        <th width="8%">客户名称</th>
	        <th width="6%">性别</th>
	        <th width="6%">身份证</th>
	        <th width="6%">职业</th>
	        <th width="6%">工作单位</th>
	        <th width="6%">联系地址</th>
	        <th width="6%">联系电话</th>
	        <th width="8%">创建日期</th>
	        <th width="8%">操作</th>
	     </tr>
        </thead>
        <tbody>
            <%--循环列表 --%>
		    <s:iterator  value="#request.list" var="v" status="status">
		     <!-- 删除链接变量 -->
		     <s:url id ="delete" action="customer_delete">
		        <s:param name="customer.id">${v.id}</s:param>
		     </s:url>
		     <!-- 更新链接变量 -->
		     <s:url id ="update" action="customer_update">
		        <s:param name="customer.id">${v.id}</s:param>
		     </s:url>	     
		     <tr <s:if test="#status.even">class='even'</s:if>>
		        <td>${v.id}</td>
		        <td>${v.name}</td>
		        <td>
		            <mt:tran data="${applicationScope.SYS_SEX}" value="${v.sex}"/>
		        </td>
		        <td>${v.idNo}</td>
		        <td>${v.vocation}</td>
		        <td>${v.workunit}</td>
		        <td>${v.address}</td>
		        <td>${v.contactTel}</td>
		        <td>
		            <s:date name="#v.createDate" format="yyyy-MM-dd"/>
		        </td>
		        <td>
		          <s:a href="%{delete}" cssClass="btn btn-mini btn-danger"
		            onclick="return confirm('你确定删除？');">删除</s:a> 
	              <s:a href="%{update}" 
	                 cssClass="btn btn-mini btn-primary">更新</s:a> 
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

