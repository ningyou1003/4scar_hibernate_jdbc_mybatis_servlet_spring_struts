var vm = avalon.define({
	$id : "areaaddCtrl",
	data : {
		id:"",
//		regioncode : "",//区域编码
		region : "",//区域名称
		parentcode : "",//父级区域编码
		parent : "",//父级区域名称
		poperlevel : "",//父级区域等级
		
		operlevel : "",
		openzoneflag : "",
		bbw_flag : "",
	},
	areaId : "",
	parentList : {},
	submit : function(){
		$("#base_form").trigger("validate");
	}
});

THISPAGE = {
	init : function() {
		this.initDom();
		this.initEvent();
		this.initData();
	},
	
	initData : function() {
		var request = Public.urlRequest();
		if (request.queryString["areaId"] != null
				&& request.queryString["areaId"].length > 0) {
			vm.areaId = request.queryString["areaId"];
			Public.ajaxPost(Public.rootPath() + "/area/editData", "areaId="
					+ vm.areaId, function(json) {
				if (json.status == 001) {
					vm.data=json.data;
				} else {
					top.openeasydialog("error", "获取区域信息失败");
				}
			});
		} else {
			
		}

	},
	
	initEvent : function() {
		$("#btback").click(function() {
			window.location = "areaList.html";
		});
		// ------------上级区域选择
		$("#ParentList").click(
				function() {
					top.openbuttondialog(
							"选择上级区域", 400, 400,
							"system/areaManage/regionTreePick.html", false,
							function(item, dialog) {
								vm.data.poperlevel = dialog.frame.$("#operlevel").val();
								if(vm.data.poperlevel==4){
									top.openeasydialog("warn", "该区域不可添加下级区域");
								}else{
									vm.data.parentcode = dialog.frame.$("#regioncode").val();
									vm.data.parent = dialog.frame.$("#region").val();
									dialog.hide();
									vm.data.operlevel = ++vm.data.poperlevel ;
									$("#ParentList").isValid();
								}
								
							});
				});
		
	},

	initDom : function() {
		document.getElementById('areaName').focus();
	}	

};
function clickOperLevel(){
	if(vm.data.parentcode.length==2&&vm.data.parentcode!="00"){
		if(vm.data.operlevel==1){
			top.openeasydialog("error", "上级是省级区域，请选择省级以下的区域级别");
			vm.data.operlevel=2;
		}
	}else if(vm.data.parentcode.length==4){
		if(vm.data.operlevel==1||vm.data.operlevel==2){
			top.openeasydialog("error", "上级是市级区域，请选择市级以下的区域级别");
			vm.data.operlevel=3;
		}
	}else if(vm.data.parentcode.length==7){
		
	}
	
}

function postData() {
	var url = null;
	if (vm.areaId != "" && vm.areaId != null && vm.areaId != undefined) {
		url = Public.rootPath() + "/area/edit";
	} else {
		url = Public.rootPath() + "/area/add";
	}
	Public.ajaxPost(url, vm.data.$model, function(json) {
		if (json.status == 001) {
			var dialog = top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
				location.href=htmlRootPath+"/app/system/areaManage/areaList.html";
				parent.cums_leftFrame.location.reload();
			});
		} else {
			top.openeasydialog("error", json.msg);
		}
	});

}

function initValidator() {
	$("#base_form").validator({
		rules : {
			regionCode : [/^[0-9]+$/,"区域编码只能由数字组成"]
		},
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