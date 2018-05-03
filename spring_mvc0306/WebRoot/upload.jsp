<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传页面</title>
</head>
<body>
	<center><h2>上传页面</h2></center>
	<form action="upload.do" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>姓名:</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>头像:</td>
				<td><input type="file" name="image"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>