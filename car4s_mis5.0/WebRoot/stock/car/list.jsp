<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>整车库存列表</title>
</head>
<body>
    <div class="alert alert-info">
                   当前位置<b class="tip"></b>库存管理<b class="tip"></b>整车库存列表
    </div>
    <form id="form01" action="${_cxt}/carStock_list.do"  method="post">
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
	           <td align="right">汽车品牌:</td>
	           <td align="left">
	             <s:textfield name="carStock.brand" cssClass="ipt"/>
	           </td>
	           <td align="right">车系:</td>
	           <td align="left">
	             <s:textfield name="carStock.series" cssClass="ipt"/>
	           </td>
         </tr>
           <tr>
	           <td align="right">类型:</td>
	           <td align="left">
	              <s:select name="carStock.type" 
                       list="#application.CAR_TYPE" cssClass="ipt"
                       headerKey="0" headerValue="--请选择--"
                       listKey="key" listValue="value">
                   </s:select>  
	           </td>
	           <td align="right">排量:</td>
	           <td align="left">
	               <s:select name="carStock.volume" 
                       list="#application.CAR_VOL" cssClass="ipt"
                       headerKey="0" headerValue="--请选择--"
                       listKey="key" listValue="value">
             </s:select>  
	           </td>
         </tr>
         <tr>
		   <td align="right"></td>
           <td align="left"></td>
           <td align="right"></td>
           <td align="left">
                   <input class="btn" type="submit" value="查询" />
                   <input class="btn" type="button" onclick="clean();" value="清空" />
                </td>
            </tr>
          </tbody>
      </table>
    <table class="tb">
        <thead>
	     <tr>
	        <th width="4%">记录ID</th>
	        <th width="6%">汽车品牌</th>
	        <th width="5%">车系</th>
	        <th width="4%">类型</th>
	        <th width="4%">排量</th>
	        <th width="6%">颜色</th>
	        <th width="6%">库存量</th>
	        <th width="6%">库存告警(&lt;10)</th>
	     </tr>
        </thead>
        <tbody>
            <%--循环列表 --%>
		    <s:iterator  value="#request.list" var="v" status="status">
		     <tr <s:if test="#status.even">class='even'</s:if>>
		        <td>${v.id}</td>
		        <td>${v.brand}</td>
		        <td>${v.series}</td>
		        <td><mt:tran data="${applicationScope.CAR_TYPE}" value="${v.type}"/></td>
		        <td><mt:tran data="${applicationScope.CAR_VOL}" value="${v.volume}"/></td>
		        <td>${v.color}</td>
		        <td>${v.count}</td>
		        <td>
		           <s:if test="#v.count > 10">
		              <span>库存正常</span>
		           </s:if>
		           <s:else>
		              <span style="color:red;">库存告警</span>
		           </s:else>
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

