var vm = avalon.define({
	$id : "templeAddCtrl",
	data : {
		keyid : "",
		type : "",
		releasetime:"",
		randomNum: randomNum,
	},
	msg : "",
	msgImg : Public.rootPath() + "/images/ticon.png",
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
	initEvent : function() {
		$("#btback").click(function() {
			window.location = "templeList.html";
		});
		
	},
	initData : function() {
		var request = Public.urlRequest();
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.data.keyid = request.queryString["keyid"];
			Public.ajaxPost(Public.rootPath() + "/temple/editData", "keyId="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data;
				} else {
					top.openeasydialog("error",json.msg);
				}
			});
		} else {
			vm.data.releasetime = Public.getNowFormatDate();
		}
	},
	initDom : function() {
	}
};
function postData() {
	var url=null;
	if(vm.data.keyid != "" && vm.data.keyid != null&& vm.data.keyid !=undefined){
		url= Public.rootPath() + "/temple/edit";
	}else{
		url= Public.rootPath() + "/temple/add";
	}
	Public.ajaxPost(url, vm.data.$model, function(
			json) {
		if (json.status == 001) {
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
				location.href=htmlRootPath+"/app/dataStatistics/templeManage/templeList.html";
			});
		} else {
			top.openeasydialog("error", json.msg);
		}
	});
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
	initUpload("temple", vm.data.keyid);
});
