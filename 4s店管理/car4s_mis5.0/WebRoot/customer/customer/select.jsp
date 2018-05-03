<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>客户列表</title>
<script type="text/javascript">
   function tconfirm(){
      var cb = $("input[name=check]:checked");
      if(cb.size()!=1){
        alert("请选择且仅选择一条记录！");
        return;
      }
	  //调用父窗口的js方法
	  parent.closeDialog_customer($(cb[0]).attr("id"),$(cb[0]).attr("value"));
   }    
</script>
</head>
<body>
    <form id="form01" action="${_cxt}/customer_select.do"  method="post">
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
                   <input class="btn" type="button" value="确定" onclick="tconfirm();"/>
                </td>
            </tr>
          </tbody>
      </table>
    <table class="tb">
        <thead>
	     <tr>
	        <th width="4%">选择</th>
	        <th width="8%">客户名称</th>
	        <th width="6%">性别</th>
	        <th width="6%">身份证</th>
	        <th width="6%">职业</th>
	        <th width="6%">工作单位</th>
	        <th width="6%">联系地址</th>
	        <th width="6%">联系电话</th>
	     </tr>
        </thead>
        <tbody>
            <%--循环列表 --%>
		    <s:iterator  value="#request.list" var="v" status="status">
		     <tr <s:if test="#status.even">class='even'</s:if>>
		        <td>
		            <input type="radio" name="check" 
                 		   id="${v.id}" value="${v.name}"/>
		        </td>
		        <td>${v.name}</td>
		        <td>
		            <mt:tran data="${applicationScope.SYS_SEX}" value="${v.sex}"/>
		        </td>
		        <td>${v.idNo}</td>
		        <td>${v.vocation}</td>
		        <td>${v.workunit}</td>
		        <td>${v.address}</td>
		        <td>${v.contactTel}</td>
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

