<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>整车进货单列表</title>
</head>
<body>
    <div class="alert alert-info">
                   当前位置<b class="tip"></b>进货管理<b class="tip"></b>整车进货单列表
    </div>
    <form id="form01" action="${_cxt}/carInorder_list.do"  method="post">
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
	             <s:textfield name="carInorder.brand" cssClass="ipt"/>
	           </td>
	           <td align="right">车系:</td>
	           <td align="left">
	             <s:textfield name="carInorder.series" cssClass="ipt"/>
	           </td>
         </tr>
         <tr>
           <td align="right">入库状态:</td>
           <td align="left">
             <s:select name="carInorder.inState" 
                       list="#application.SYS_IN_STATE" cssClass="ipt"
                       headerKey="0" headerValue="--请选择--"
                       listKey="key" listValue="value">
             </s:select>  
           </td>
           <td align="right">供应商:</td>
           <td align="left">
             <s:select name="carInorder.supplierId" 
                       list="#application.supplierMap" cssClass="ipt"
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
                   <input class="btn" type="button" value="新增" 
                          onclick="location.href='${_cxt}/inorder/car/add.jsp'"/>
                </td>
            </tr>
          </tbody>
      </table>
    <table class="tb">
        <thead>
	     <tr>
	        <th width="4%">记录ID</th>
	        <th width="6%">汽车品牌-车系</th>
	        <th width="8%">供应商</th>
	        <th width="5%">进货价</th>
	        <th width="4%">数量</th>
	        <th width="4%">总价</th>
	        <th width="6%">填单日期</th>
	        <th width="6%">入库日期</th>
	        <th width="6%">入库状态</th>
	        <th width="10%">操作</th>
	     </tr>
        </thead>
        <tbody>
            <%--循环列表 --%>
		    <s:iterator  value="#request.list" var="v" status="status">
		     <!-- 删除链接变量 -->
		     <s:url id ="delete" action="carInorder_delete">
		        <s:param name="carInorder.id">${v.id}</s:param>
		     </s:url>
		     <!-- 更新链接变量 -->
		     <s:url id ="update" action="carInorder_update">
		        <s:param name="carInorder.id">${v.id}</s:param>
		     </s:url>	     
		     <!-- 车入库链接变量 -->
		     <s:url id ="ruKu" action="carInorder_ruKu">
		        <s:param name="carInorder.id">${v.id}</s:param>
		     </s:url>	     
		     <tr <s:if test="#status.even">class='even'</s:if>>
		        <td>${v.id}</td>
		        <td>${v.brand}－${v.series}</td>
		        <td>${v.supName}</td>
		        <td>${v.inPrice}万</td>
		        <td>${v.count}</td>
		        <td>${v.total}万</td>
		        <td>
		           <s:date name="#v.createDate" format="yyyy-MM-dd"/>
		        </td>
		        <td>
		           <s:date name="#v.inDate" format="yyyy-MM-dd"/>
		        </td>
		        <td>
		           <mt:tran data="${applicationScope.SYS_IN_STATE}" value="${v.inState}"/>
		        </td>		        
		        <td>
		          <s:if test="#v.inState == 1">
		            <s:a href="%{delete}" cssClass="btn btn-mini btn-danger"
		               onclick="return confirm('你确定删除？');">删除</s:a> 
	                <s:a href="%{update}" cssClass="btn btn-mini btn-primary">更新</s:a>
	                <s:a href="%{ruKu}" cssClass="btn btn-mini btn-primary"
	                    onclick="return confirm('你确定入库？');">入库</s:a>
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

