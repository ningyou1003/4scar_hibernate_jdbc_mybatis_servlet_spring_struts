<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改密码</title>
<script type="text/javascript">
	function yz() {
		var x1 = document.getElementById("p1").value;
		var x2 = document.getElementById("p2").value;

		if (x1 != x2) {
			alert("密码不一致，请重新输入！");
			return false;
		}else if(x1==''){
			alert("密码不能为空，请重新输入！");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form action="${_cxt}/user_pwdupdate.do" method="post"
		onsubmit="return yz();">
		<!-- 隐藏域 用于判断是不是提交表单-->
		<s:hidden name="commit" value="true" />
		<%-- 隐藏域 待更新记录的ID--%>
		<s:hidden name="user.id" />
		<table class="tbform list">
			<thead onclick="collapse(this);">
				<tr class="tr">
					<th colspan="4" style="text-align: left;">修改密码<i
						class="tip-up"></i></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="right">输入新密码:</td>
					<td align="left"><s:textfield name="user.newpassword"
							cssClass="ipt" id="p1" /></td>

				</tr>
				<tr>
					<td align="right">确认新密码:</td>
					<td align="left"><s:textfield name="user.password1"
							cssClass="ipt" id="p2" /></td>

				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input class="btn" type="submit" value="确定" /> 
						<input class="btn" type="reset" value="清空" /> 
						<input class="btn" type="button" value="返回"
							onclick="history.back()" />
						
					</td>
					
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>

