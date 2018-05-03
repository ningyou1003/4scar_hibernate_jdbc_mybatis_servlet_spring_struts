<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp" %>
<head>
    <title>反馈更新页面</title>
<script type="text/javascript">
  var dialog;
  //把url对应页面在弹出窗口中打开
  function showDialog(url,title){
     alert(url);
     dialog = $.dialog({
        title:title,
     	width: '800px',
    	height:'300px',
    	content: 'url:'+url
	 });
  }
  //关闭
  function closeDialog_customer(customerId,customerName){
    alert(customerId + " - "+customerName);
    $("#customerName").val(customerName);
    $("#customerId").val(customerId);
    dialog.close();
  }
</script>
</head>
<body>
    <form action="${_cxt}/feedback_update.do" method="post">
     <!-- 隐藏域 用于判断是不是提交表单-->
     <s:hidden name="commit" value="true"/>
     <%-- 隐藏域 待更新记录的ID--%>
     <s:hidden name="feedback.id"/>
     <!-- 弹出列表链接 -->
     <s:url id="select" action="customer_select" namespace="/"/>
    <table class="tbform list">
          <thead onclick="collapse(this);">
            <tr class="tr">
              <th colspan="4" style="text-align: left;">
                                         反馈更新<i class="tip-up"></i>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td align="right">反馈主题:</td>
              <td align="left">
                 <s:textfield name="feedback.title" cssClass="ipt"/>
              </td>
              <td align="right">客户姓名:</td>
              <td align="left">
                 <s:hidden id="customerId" name="feedback.customerId"/>
                 
                 <input type="text" id="customerName" value="${feedback.name}"
                        readonly="readonly" class="ipt"/>
                 <s:a href="#" onclick="showDialog('%{select}','选择一个客户')">选择</s:a>
              </td>              
            </tr>
           <tr>
             <td align="right">反馈信息:</td>
             <td align="left" colspan="3">
               <s:textarea name="feedback.info" cols="108"/>
             </td>
           </tr>  
            <tr>
                <td colspan="2"></td>
                <td colspan="2" align="center">
                   <input class="btn" type="submit" value="添加" />
                   <input class="btn" type="reset" value="清空" />
                   <input class="btn" type="button" value="返回" onclick="history.back()"/>
                </td>
            </tr>
          </tbody>
      </table>
      </form>
</body>
</html>

