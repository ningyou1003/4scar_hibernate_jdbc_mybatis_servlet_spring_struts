<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册页面</title>
</head>
<body>
	<center><h2>用户注册页面</h2></center>
	<form action="user/add.do" method="post">
		<table>
			<tr>
				<td>姓名:</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>年龄:</td>
				<td><input type="text" name="age"></td>
			</tr>
			<tr>
				<td>出生日期:</td>
				<td><input type="text" name="birthday"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="注册"></td>
			</tr>
		</table>
	</form>
</body>
</html>