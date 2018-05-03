<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- struts2的标签库引入 -->
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title>结果显示</title>
<!-- js库 -->
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	function ajax_do(){
		$.ajax(
			{
				type:"post",
				url:"ajaxAction.do",
				date:"",
				success:function(msg){//处理服务器返回信息
					alert(msg);
					$("#time").html(msg);
				}
			}
		);
	}
</script>
  </head>
  <body>
  	<h2>AJAX测试页面（struts2）</h2>
  	<input type="button" value="提交" onclick="ajax_do();">
  	<div id="time"></div>
  </body>
</html>
