<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>整车销售单列表</title>
</head>
<body>
    <div class="alert alert-info">
                   当前位置<b class="tip"></b>销售管理<b class="tip"></b>整车销售单列表
    </div>
    <form id="form01" action="${_cxt}/carSellorder_list.do"  method="post">
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
	             <s:textfield name="carSellorder.brand" cssClass="ipt"/>
	           </td>
	           <td align="right">车系:</td>
	           <td align="left">
	             <s:textfield name="carSellorder.series" cssClass="ipt"/>
	           </td>
         </tr>
         <tr>
           <td align="right">提车状态:</td>
           <td align="left">
             <s:select name="carSellorder.outState" 
                       list="#application.SYS_OUT_STATE" cssClass="ipt"
                       headerKey="0" headerValue="--请选择--"
                       listKey="key" listValue="value">
             </s:select>  
           </td>
           <td align="right">销售人员:</td>
           <td align="left">
				<s:textfield name="carSellorder.salesman" cssClass="ipt"/>  
           </td>
         </tr>
         <tr>
		   <td align="right"></td>
           <td align="left"></td>
           <td align="right"></td>
           <td align="left">
                   <input class="btn" type="submit" value="查询" />
                   <input class="btn" type="button" onclick="clean();" value="清空" />
                   <input class="btn" type="button" value="新增" 
                          onclick="location.href='${_cxt}/sellorder/car/add.jsp'"/>
                </td>
            </tr>
          </tbody>
      </table>
    <table class="tb">
        <thead>
	     <tr>
	        <th width="4%">记录ID</th>
	        <th width="6%">汽车品牌-车系</th>
	        <th width="5%">销售价</th>
	        <th width="4%">数量</th>
	        <th width="4%">总价</th>
	        <th width="6%">填单日期</th>
	        <th width="6%">提车日期</th>
	        <th width="6%">提车状态</th>
	        <th width="6%">销售人员</th>
	        <th width="10%">操作</th>
	     </tr>
        </thead>
        <tbody>
            <%--循环列表 --%>
		    <s:iterator  value="#request.list" var="v" status="status">
		     <!-- 删除链接变量 -->
		     <s:url id ="delete" action="carSellorder_delete">
		        <s:param name="carSellorder.id">${v.id}</s:param>
		     </s:url>
		     <!-- 更新链接变量 -->
		     <s:url id ="update" action="carSellorder_update">
		        <s:param name="carSellorder.id">${v.id}</s:param>
		     </s:url>	     
		     <!-- 提车链接变量 -->
		     <s:url id ="tiHuo" action="carSellorder_tiHuo">
		        <s:param name="carSellorder.id">${v.id}</s:param>
		     </s:url>	     
		     <tr <s:if test="#status.even">class='even'</s:if>>
		        <td>${v.id}</td>
		        <td>${v.brand}－${v.series}</td>
		        <td>${v.sellPrice}万</td>
		        <td>${v.count}</td>
		        <td>${v.total}万</td>
		        <td>
		           <s:date name="#v.sellDate" format="yyyy-MM-dd"/>
		        </td>
		        <td>
		           <s:date name="#v.outDate" format="yyyy-MM-dd"/>
		        </td>
		        <td>
		           <mt:tran data="${applicationScope.SYS_OUT_STATE}" value="${v.outState}"/>
		        </td>		        
		        <td>${v.salesman}</td>		        
		        <td>
		          <s:if test="#v.outState == 1">
		            <s:a href="%{delete}" cssClass="btn btn-mini btn-danger"
		               onclick="return confirm('你确定删除？');">删除</s:a> 
	                <s:a href="%{update}" cssClass="btn btn-mini btn-primary">更新</s:a>
	                <s:a href="%{tiHuo}" cssClass="btn btn-mini btn-primary"
	                     onclick="return confirm('你确定提车？');"
	                >提车</s:a>
	              </s:if>
	              <s:else>无操作</s:else>
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

