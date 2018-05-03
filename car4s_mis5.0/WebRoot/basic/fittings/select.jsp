<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>配件列表</title>
<script type="text/javascript">
   function tconfirm(){
      var cb = $("input[name=check]:checked");
      if(cb.size()!=1){
        alert("请选择且仅选择一条记录！");
        return;
      }
	  //调用父窗口的js方法
	  parent.closeDialog_fit($(cb[0]).attr("id"),$(cb[0]).attr("value"));
   }    
</script>
</head>
<body>
    <form id="form01" action="${_cxt}/fittings_select.do" method="post">
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
           <td align="right">配件名称:</td>
           <td align="left">
             <s:textfield name="fittings.name" cssClass="ipt"/>
           </td>
           <td align="right">单位:</td>
           <td align="left">
             <s:select name="fittings.unit"
                       list="#application.CAR_UNIT" cssClass="ipt"
                       headerKey="0" headerValue="--请选择--"/>
           </td>
         </tr>
         <tr>
           <td align="right">型号:</td>
           <td align="left">
             <s:textfield name="fittings.type" cssClass="ipt"/>
           </td>
           <td align="right"></td>
           <td align="left">
                   <input class="btn" type="submit" value="查询" />
                   <input class="btn" type="button" onclick="clean();" value="清空" />
                   <input class="btn" type="button" onclick="tconfirm();" value="确定" />
            </td>
           </tr>
          </tbody>
      </table>
    <table class="tb">
        <thead>
	     <tr>
	        <th width="4%">选择</th>
	        <th width="8%">配件名称</th>
	        <th width="4%">单位</th>
	        <th width="4%">价格</th>
	        <th width="6%">品牌</th>
	        <th width="6%">型号</th>
	        <th width="8%">创建日期</th>
	     </tr>
        </thead>
        <tbody>
		<%--循环列表 --%>
		    <s:iterator  value="#request.list" var="v" status="status">
		     <tr <s:if test="#status.even">class='even'</s:if>>
		        <td>
		           <input type="radio" name="check" 
                          id="${v.id}" 
                          value="${v.name}-${v.brand}-${v.type}"/>
		        </td>
		        <td>${v.name}</td>
		        <td>
		          <mt:tran data="${applicationScope.CAR_UNIT}" value="${v.unit}"/>
		        </td>
		        <td>${v.price}</td>
		        <td>${v.brand}</td>
		        <td>${v.type}</td>
		        <td>
		            <s:date name="#v.createDate" format="yyyy-MM-dd"/>
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

