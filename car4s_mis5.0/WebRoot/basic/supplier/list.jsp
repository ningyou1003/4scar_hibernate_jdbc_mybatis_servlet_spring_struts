<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>厂商列表</title>
</head>
<body>
    <div class="alert alert-info">
                   当前位置<b class="tip"></b>基础管理<b class="tip"></b>厂商列表
    </div>
    <form id="form01" action="${_cxt}/supplier_list.do"  method="post">
    <!-- 隐藏域 用于判断是不是提交表单-->
    <s:hidden name="commit" value="true"/>
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">
                                         查询条件 <i class="tip-up"></i>
                 <div style="color:red;float: right;">
	             ${message}
	             </div>
              </th>
            </tr>
          </thead>
          <tbody>
          <tr>
           <td align="right">厂商名称:</td>
           <td align="left">
             <s:textfield name="supplier.name" cssClass="ipt"/>
           </td>
           <td align="right">联系人:</td>
           <td align="left">
             <s:textfield name="supplier.contacts" cssClass="ipt"/>
           </td>
         </tr>
         <tr>
           <td align="right">开户银行:</td>
           <td align="left">
             <s:textfield name="supplier.bankName" cssClass="ipt"/>
           </td>
           <td colspan="2" align="center">
                   <input class="btn" type="submit" value="查询" />
                   <input class="btn" type="button" onclick="clean();" value="清空" />
                   <input class="btn" type="button" value="新增" 
                          onclick="location.href='${_cxt}/basic/supplier/add.jsp'"/>
            </td>
           </tr>
          </tbody>
      </table>
    <table class="tb">
        <thead>
	     <tr>
	        <th width="6%">记录ID</th>
	        <th width="8%">供应商名称</th>
	        <th width="6%">联系人</th>
	        <th width="6%">联系电话</th>
	        <th width="6%">开户银行</th>
	        <th width="6%">银行账号</th>
	        <th width="8%">创建日期</th>
	        <th width="8%">操作</th>
	     </tr>
        </thead>
        <tbody>
            <%--循环列表 --%>
		    <s:iterator  value="#request.list" var="v" status="status">
		     <!-- 删除链接变量 -->
		     <s:url id ="delete" action="supplier_delete">
		        <s:param name="supplier.id">${v.id}</s:param>
		     </s:url>
		     <!-- 更新链接变量 -->
		     <s:url id ="update" action="supplier_update">
		        <s:param name="supplier.id">${v.id}</s:param>
		     </s:url>	     
		     <tr <s:if test="#status.even">class='even'</s:if>>
		        <td>${v.id}</td>
		        <td>${v.name}</td>
		        <td>${v.contacts}</td>
		        <td>${v.contactTel}</td>
		        <td>${v.bankName}</td>
		        <td>${v.bankAccount}</td>
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

