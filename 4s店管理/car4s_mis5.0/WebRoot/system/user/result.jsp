<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示结果</title>
<script type="text/javascript">
   function tconfirm(){
   		alert("${msg}");
   		if(!"${msg}"==''){
   			alert(1);
   		}
		  //parent代表父窗口对象
		  parent.closeDialog();
   }
</script>

</head>
<body onload="tconfirm();">
	
</body>
</html>