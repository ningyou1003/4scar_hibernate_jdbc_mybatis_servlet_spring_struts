var manager;
var vm = avalon.define({
	$id : "roleeditCtrl",
	data : {
		keyid : "",
		name : "",
		orderid : "",
		par : "",
		level : ""
	},
	selected :"",
	Rolelevel : ["省/自治区/直辖市级","市级","县/区级","乡/镇级","基层"],
	regioncode : "",
	msg : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
	submit : function() {
		$("#base_form").trigger("validate");
	}
});
var level;
THISPAGE = {
	init : function() {
		this.initDom();
		this.initData();
		this.initDataGrid();
		this.initEvent();
	},
	initEvent : function() {
		$("#btback").click(function() {
			window.location = "roleList.html";
		});
		$("#region").click(
				function() {
					top.openbuttondialog("选择区域", 400, 400,
							"system/areaManage/regionTreePick.html", false,
							function(item, dialog) {
								vm.data.regioncode = dialog.frame.$(
										"#regioncode").val();
								vm.data.region = dialog.frame.$("#region")
										.val();
								dialog.close();
								$("#region").isValid();
							});
				});
		// 为ｌｉｇｅｒＧｒｉｄ注册点击时间，控件中的注册在ＩＥ８中无效，ligerGrid.js中
		// $(document).bind("click.grid", function (e) {
		// g._onClick.call(g, e);
		// });已经被注释
		$("#maingrid").click(function(e) {
			manager._onClick(e);
		});

	},
	initData : function() {
		var request = Public.urlRequest();
		var regioncode = vm.regioncode = request.queryString["regioncode"];
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.data.keyid = request.queryString["keyid"];
			Public.ajaxPost(Public.rootPath() + "/role/editData", "keyid="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data = json.data;
					level = vm.data.level ;
					selectLevel(level);
				} else {
					top.openeasydialog("error", "角色信息获取失败");
				}
			});
		} 

	},
	initDom : function() {
		document.getElementById('roleName').focus();
	},
	initDataGrid : function() {

		manager = $("#maingrid")
				.ligerGrid(
						{
							columns : [
									{
										display : 'KeyID',
										name : 'keyid',
										width : 10,
										align : 'left',
										editor : {
											type : 'text'
										}
									},
									{
										display : '模块名称',
										name : 'name',
										width : 300,
										align : 'left',
										editor : {
											type : 'text'
										}
									},
									/*{
										display : '菜单可见权限',
										name : 'lookpower',
										width : 100,
										align : 'center',
										render : function(item, rowindex,
												value, column) {
											var check = item.lookpower == "1" ? "checked=\"checked\"": "";
											var inputValue = item.keyid + "_LookPower";
											return "&nbsp;<input type=\"checkbox\" value='" + inputValue+ "'  "
													+ check
													+ " id='LookPower_"
													+ item.keyid
											        + "' onclick=\"selcheckbox(this)\" />";
										}
									},*/
									{
										display : '操作权限',
										name : 'addpower',
										width : 300,
										align : 'left',
										render : function(item, rowindex, value, column) {
											var bizPermission = item.bizpermission;
											var bStr = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
											var finalStr = "";
											for(var i=0; i<bizPermission.length; i++){
												var check = bizPermission[i].isSelect == "1" ? "checked=\"checked\"":"";
												var inputValue = item.keyid + "_" + bizPermission[i].key;
												finalStr+="<input type='checkbox' value='"+inputValue+"' "+check+"/>" + bizPermission[i].name + "&nbsp;"
											}
											return bStr+finalStr;
										}
									},
									{
									    display: '全选&nbsp;<input name=\"checkAll_all\" type=\"checkbox\" onclick=\"checkAllBox_All(this)\"/>',
										width : 100,
										align : 'center',
										render : function(item, rowindex,
												value, column) {
											return "&nbsp;<input name=\"checkAll\" type=\"checkbox\" onclick=\"checkAllBox(this)\"/>";
										}
									},
							],
							width : '60%',
							height : '97%',
							url : Public.rootPath()
									+ "/role/loadRolePowerEdit?keyid="
									+ vm.data.keyid,
							// data:TreeDeptData,
							dataAction: 'local',//本地排序
							usePager : false,
							method : 'get',
							alternatingRow : false,
							tree : {
								columnName : 'name', idField:'keyid', parentIDField:'parentid'
							},
							checkbox : false,
							autoCheckChildren : false,
							allowAdjustColWidth: false,
							enabledEdi: false,
							allowHideColumn: false
						});
		manager.toggleCol("keyid", false); // 是否显示该列
	}
};

function postData() {
	var content = "";
	if(vm.selected=="省/自治区/直辖市级"){
		vm.data.level = 0;
	} else if(vm.selected=="市级"){
		vm.data.level = 1;
	} else if(vm.selected=="县/区级"){
		vm.data.level = 2;
	} else if(vm.selected=="乡/镇级"){
		vm.data.level = 3;
	} else {
		vm.data.level = 4;
	}
	var ci = 0;
	$("input[type=checkbox]").each(function() { // 由于复选框一般选中的是多个,所以可以循环输出
		var v = $(this).prop("value");
		if (v!="" && v!=undefined && v!="on") {
			content += $(this).prop("checked") ? v+"," : "";
		}

		//if ($(this).attr('id') != "" && $(this).attr('id') != undefined) {
			//var v = $(this).prop("checked") ? "1" : "0";
			//content += v + "_" + $(this).attr('id') + "@";
			//ci++;
			//if (ci == 5) {
			//	ci = 0;
			//	content += ",";
			//}
		//}
	});
	vm.data.par = content;
	var url = null;
	if (vm.data.keyid != "" && vm.data.keyid != null && vm.data.keyid != undefined) {
		url = Public.rootPath() + "/role/edit";
	} else {
		url = Public.rootPath() + "/role/add";
	}
	Public.ajaxPost(url, vm.data.$model, function(
			json) {
		if (json.status == 001) {
			/*if($("#Powerdiv").hide()){
				$("#Powerdiv").show();
			}*/
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
				location.href=htmlRootPath+"/app/system/roleManage/roleList.html?keyid="+vm.data.regioncode;
			});
		} else {
			top.openeasydialog("error", json.msg);
		}
	});
}

// 选中
function selcheckbox(e) {
	if ($(e).prop("checked")) {
		$("input[id^='" + $(e).attr('id') + "']").prop("checked", "true");
	} else {
		$("input[id^='" + $(e).attr('id') + "']").removeAttr("checked");
	}
}
//权限全选
function checkAllBox(e){

	if ($(e).prop("checked")) {
        $(e).parents().siblings("td").find("input[type='checkbox']").prop("checked", true);
    } else {
        $(e).parents().siblings("td").find("input[type='checkbox']").prop("checked", false);
    }
	
}

function checkAllBox_All(e) {
	if ($(e).prop("checked")) {
	    $("input[type='checkbox']").prop("checked", true);
	} else {
	    $("input[type='checkbox']").prop("checked", false);
    }
}

function selectLevel(level){
	if(level==0){
		vm.selected = "省/自治区/直辖市级";
	} else if(level==1){
		vm.selected = "市级";
	} else if(level== 2){
		vm.selected = "县/区级";
	} else if(level== 3){
		vm.selected = "乡/镇级";
	} else {
		vm.selected = "基层";
	}
}

function initValidator() {
	$("#base_form").validator({
		messages : {
			required : "请填写{0}"
		},
		display : function(e) {
			var text = $(e).closest(".row-item").find("label").text().trim();
			return text.substring(0, text.length - 2);
		},
		valid : function() {
			postData();
		},
		ignore : ":hidden",
		theme : "yellow_bottom",
		timely : 1,
		stopOnError : true
	});
}
$(document).ready(function(e) {
	// 需要先初始化表单验证配置
	initValidator();
	THISPAGE.init();
});
