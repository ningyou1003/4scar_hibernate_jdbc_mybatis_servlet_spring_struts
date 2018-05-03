<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单信息管理</title>
<!-- 引入外部Css 
<link href="${_css}/global.css" rel="stylesheet" type="text/css"/>-->
<link rel="stylesheet" href="${_plugins}/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<link rel="stylesheet" href="${_plugins}/ztree/css/demo.css" type="text/css"/>
<script type="text/javascript" src="${_plugins}/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${_plugins}/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${_plugins}/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
	    //树的设置
		var setting = {
			view: {
				selectedMulti: false
			},
			check: {
				enable: true,
				chkboxType:{ "Y" : "ps", "N" : "s" }
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				//beforeCheck: beforeCheck,
				//onCheck: onCheck
			}
		};
		//JSON数据
		var zNodes = ${requestScope.json};
		
		//var code, log, className = "dark";
		//
/* 		function beforeCheck(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			alert(treeNode.id);
			return (treeNode.doCheck !== false);
		} */
		//
/* 		function onCheck(e, treeId, treeNode) {
			//获取隐藏域对象
		    var obj = $("#menuIds");
			if(treeNode.checked){
			  obj.val(obj.val() + "-" + treeNode.id+"-");
			}else{
			  obj.val(obj.val().replace("-"+treeNode.id+"-",""));
			}
		} */
		//全选和取消全选
	    function checkNode(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			type = e.data.type;

			if (type == "checkAllTrue") {
				zTree.checkAllNodes(true);
			} else if (type == "checkAllFalse") {
				zTree.checkAllNodes(false);
			} 
		}
		//生成树并绑定事件监听
		$(document).ready(function(){
			//生成树
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$("#checkAllTrue").bind("click", {type:"checkAllTrue"}, checkNode);
			$("#checkAllFalse").bind("click", {type:"checkAllFalse"}, checkNode);
		});
		//提交表单
		function addSubmit(){
		  	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		  	//获取所有被勾选择节点
			nodes = zTree.getCheckedNodes();
			//ID字符串
			var menuIds = "";
			var roleId =${requestScope.roleId};//角色Id
			//把所有打勾的项拼接成字符串
			$.each(nodes,function(index,obj) {
			  if(index!=0){
			  	menuIds += "," + obj.id;
			  }else{
			    menuIds += obj.id;
			  }              
            });
            //提交表单
		    $.ajax({
			       type : "post",
				   url  : "${_cxt}/menu_updateRole2Menu.do",
				   cache: false,
				   data : "menuIds="+menuIds+"&roleId=" + roleId,
				   success : function(jsonStr) {
				      //把json字符串转化成json对象
					  var json = eval("(" + jsonStr + ")");
					  //alert(json.result);
					  //关闭当前窗口
					  parent.closeDialog(json.result);
				   }
			  });
		}
		//关闭窗口
		function cancel(){
		   //关闭当前窗口
		   parent.closeDialog("");
		}
	</script>
</head>
<body>
<table width="100%" border="0" align="center">
  <tr>
     <td align="center">
        [<a id="checkAllTrue" style="text-decoration: none;" href="#" onclick="return false;">全选</a>]
		[<a id="checkAllFalse" style="text-decoration: none;" href="#" onclick="return false;">取消全选</a>] 
		[<a href="#" style="text-decoration: none;" onclick="addSubmit();">确定分配</a>]
		[<a href="#" style="text-decoration: none;" onclick="cancel();">取消</a>]
		<ul id="treeDemo" class="ztree"></ul>
     </td>
  </tr>
</table>
</body>
</html>
