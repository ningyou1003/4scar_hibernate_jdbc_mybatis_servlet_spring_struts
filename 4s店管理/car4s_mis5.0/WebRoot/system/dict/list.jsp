<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/header.jsp"%>
<head>
<title>字典列表</title>
<script type="text/javascript">
function clean() {
 //alert(1);
 document.getElementById("d1").value = "";
 document.getElementById("d2").value = "0";
 document.getElementById("d3").value = ""; 
}
</script>
</head>
<body>
	<div class="alert alert-info">
		当前位置<b class="tip"></b>系统管理<b class="tip"></b>字典列表
	</div>
	<form id="fm1" action="${_cxt}/dict_list.do" method="post">
		<!-- 隐藏域 用于判断是不是提交表单-->
		<s:hidden name="commit" value="true" />
		<table class="tbform list">
			<thead onclick="collapse(this);">
				<tr class="tr">
					<th colspan="4" style="text-align: left;">查询条件 <i class="tip-up"></i>
						<div style="color:red;float: right;">
	            			${msg}
	             	    </div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="right">字典名称:</td>
					<td align="left"><s:textfield name="dict.dictName"
							cssClass="ipt" id="d1" /></td>
					<td align="right">可用标签:</td>
					<td align="left"><s:select list="#application.SYS_USE_FLAG"
							name="dict.useFlag" cssClass="ipt" headerKey="0"
							headerValue="--请选择--" id="d2" /></td>
				</tr>
				<tr>
					<td align="right">字典KEY:</td>
					<td align="left"><s:textfield name="dict.key" cssClass="ipt" id="d3" />
					</td>
					<td align="right"></td>
					<td align="left"><input class="btn" type="submit" value="查询" />
						<input class="btn" type="button" onclick="clean();" value="清空" />
						<input class="btn" type="button" value="新增"
						onclick="location.href='${_cxt}/system/dict/add.jsp'" /></td>
				</tr>
			</tbody>
		</table>
		<table class="tb">
			<thead>
				<tr>
					<th width="6%">记录ID</th>
					<th width="8%">字典名称</th>
					<th width="6%">字典key</th>
					<th width="6%">字典value</th>
					<th width="6%">可用标志</th>
					<th width="6%">顺序号</th>
					<th width="8%">创建日期</th>
					<th width="8%">操作</th>
				</tr>
			</thead>
			<tbody>
				<%--循环列表 --%>
				<s:iterator value="#request.list" var="v" status="status">
					<!-- 删除链接变量 -->
					<s:url id="delete" action="dict_delete">
						<s:param name="dict.id">${v.id}</s:param>
					</s:url>
					<!-- 更新链接变量 -->
					<s:url id="update" action="dict_update">
						<s:param name="dict.id">${v.id}</s:param>
					</s:url>
					<tr <s:if test="#status.even">class='even'</s:if>>
						<td>${v.id}</td>
						<td>${v.dictName}</td>
						<td>${v.key}</td>
						<td>${v.value}</td>
						<td><mt:tran data="${application.SYS_USE_FLAG}"
								value="${v.useFlag}" /></td>
						<td>${v.orderNo}</td>
						<td><s:date name="#v.createDate" format="yyyy-MM-dd" /></td>
						<td><s:a href="%{delete}" cssClass="btn btn-mini btn-danger"
								onclick="return confirm('你确定删除？');">删除</s:a> 
								<s:a href="%{update}" cssClass="btn btn-mini btn-primary">更新</s:a></td>
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

