<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>整车进货单更新页面</title>
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
  function closeDialog_car(carId,carInfo){
    $("#carId").val(carId);
    $("#carInfo").val(carInfo);
    dialog.close();
  }
</script>
</head>
<body>
    <form action="${_cxt}/carInorder_update.do" method="post">
    <!-- 隐藏域 用于判断是不是提交表单-->
    <s:hidden name="commit" value="true"/>
    <%-- 隐藏域 待更新记录的ID--%>
    <s:hidden name="carInorder.id"/>
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">整车进货单更新
                <i class="tip-up"/>
              </th>
            </tr>
          </thead>
          <tbody>
         <tr>
           <td align="right">整车信息:</td>
           <td align="left">
             <s:hidden id="carId" name="carInorder.carId"/>
             <s:textfield cssClass="ipt" id="carInfo" readonly="true"
                  value="%{carInorder.brand + '-' + carInorder.series}"/>
             <!-- 弹出列表链接 -->
       		 <s:url id="select" action="car_select" namespace="/"/>
             <s:a href="#" onclick="showDialog('%{select}','选择一个整车')">选择</s:a>
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
           <td align="right">数量:</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="carInorder.count"/>  
           </td>
           <td align="right">进货价(万):</td>
           <td align="left">
             <s:textfield cssClass="ipt" name="carInorder.inPrice"/>           
           </td>
         </tr>
         <tr>
           <td align="right">备注:</td>
           <td align="left" colspan="3">
             <s:textarea name="carInorder.remark" cols="108"/>
           </td>
         </tr>  
            <tr>
                <td colspan="2"></td>
                <td colspan="2" align="center">
                   <input class="btn" type="submit" value="提交" />
                   <input class="btn" type="reset" value="清空" />
                   <input class="btn" type="button" value="返回" onclick="history.back()"/>
                </td>
            </tr>
          </tbody>
      </table>
      </form>
</body>
</html>

