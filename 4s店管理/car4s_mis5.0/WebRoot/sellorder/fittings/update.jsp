<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>配件销售单更新页面</title>
<script type="text/javascript">
  var dialog;
  //把url对应页面在弹出窗口中打开
  function showDialog(url,title){
     dialog = $.dialog({
        title:title,
     	width: '700px',
    	height:'400px',
    	content: 'url:'+url
	 });
  }
  //关闭
  function closeDialog_fit(fitId,value){
    $("#fitId").val(fitId);
    var arr = value.split("-");
    $("#fitName").val(arr[0]);
    $("#fitBrand").val(arr[1]);
    $("#fitType").val(arr[2]);
    dialog.close();
  }
    //关闭
  function closeDialog_customer(custId,custName){
    $("#custId").val(custId);
    $("#custName").val(custName);
    dialog.close();
  }
</script>
</head>
<body>
    <form action="${_cxt}/fitSellorder_update.do" method="post">
     <!-- 隐藏域 用于判断是不是提交表单-->
     <s:hidden name="commit" value="true"/>
     <%-- 隐藏域 待更新记录的ID--%>
     <s:hidden name="fitSellorder.id"/>
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">
                                         配件销售单更新<i class="tip-up"></i>
              </th>
            </tr>
          </thead>
          <tbody>
         <tr>
           <td align="right">配件名称:</td>
           <td align="left">
             <s:hidden id="fitId" name="fitSellorder.fittingsId"/>
             <s:textfield cssClass="ipt" id="fitName" readonly="true" 
                          value="%{fitSellorder.fitName}"/>
             <!-- 弹出列表链接 -->
       		 <s:url id="select" action="fittings_select" namespace="/"/>
             <s:a href="#" onclick="showDialog('%{select}','选择一个配件')">选择</s:a>
           </td>
           <td align="right">品牌:</td>
           <td align="left">
             <s:textfield cssClass="ipt" id="fitBrand" readonly="true" value="%{fitSellorder.brand}"/>
           </td>
         </tr>
         <tr>
           <td align="right">型号:</td>
           <td align="left">
             <s:textfield cssClass="ipt" id="fitType" readonly="true" value="%{fitSellorder.type}"/>
           </td>
           <td align="right">客户名称:</td>
           <td align="left">
              <s:hidden id="custId" name="fitSellorder.customerId"/>
              <s:textfield cssClass="ipt" id="custName" readonly="true" 
                          value="%{fitSellorder.custName}"/>
              <!-- 弹出列表链接 -->
       		  <s:url id="select" action="customer_select" namespace="/"/>
              <s:a href="#" onclick="showDialog('%{select}','选择一个客户')">选择</s:a>
           </td>
         </tr>
         <tr>
           <td align="right">数量:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="fitSellorder.count"/>  
           </td>
           <td align="right">销售价(元):</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="fitSellorder.sellPrice"/>           
           </td>
         </tr>
         <tr>
           <td align="right">销售人员:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="fitSellorder.salesman"/>  
           </td>
           <td align="right"></td>
           <td align="left">
           </td>
         </tr>
         <tr>
           <td align="right">备注:</td>
           <td align="left" colspan="3">
             <s:textarea name="fitSellorder.remark" cols="105"/>
           </td>
         </tr>
         <tr>
            <td colspan="2"></td>
            <td colspan="2" align="center">
              <input class="btn" type="submit" value="更新" />
              <input class="btn" type="reset" value="清空" />
              <input class="btn" type="button" value="返回" onclick="history.back()"/>
            </td>
          </tr>
        </tbody>
      </table>
      </form>
</body>
</html>

