var vm = avalon.define({
	$id : "jobaddCtrl",
	data : {
		keyid : "",
		name : "",
		msg : "",
		regioncode : "",
		region : "",
		order : ""
	},
	regioncode : "",
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
			window.location = "jobList.html?keyid="+vm.regioncode;
		});
		$("#region").click(
				function() {
					top.openbuttondialog("请选择区域", 400, 400,
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
	},
	initData : function() {
		var request = Public.urlRequest();
		var regioncode = vm.regioncode = request.queryString["regioncode"];
		if (request.queryString["keyid"] != null
				&& request.queryString["keyid"].length > 0) {
			vm.data.keyid = request.queryString["keyid"];
			Public.ajaxPost(Public.rootPath() + "/job/editData", "keyid="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data;
				} else {
					top.openeasydialog("error",json.msg);
				}
			});
		} else {
			if(regioncode=='undefined'){
				regioncode="";
			}
			Public.ajaxPost(Public.rootPath() + "/area/getArea",{"regioncode":regioncode},function(json){
				if(json.status==1){
					vm.data.regioncode = json.data.regioncode;
					vm.data.region = json.data.region;
				}
			})
		}

	},
	initDom : function() {
		document.getElementById('Name').focus();
	}
};
function postData() {
	var url=null;
	if(vm.data.keyid != "" && vm.data.keyid != null&& vm.data.keyid !=undefined){
		url= Public.rootPath() + "/job/edit";
	}else{
		url= Public.rootPath() + "/job/add";
	}
	Public.ajaxPost(url, vm.data.$model, function(
			json) {
		if (json.status == 001) {
			top.openeasydialog("success", json.msg, function(item, dialog){
				dialog.close();
				location.href=htmlRootPath+"/app/system/jobManage/jobList.html";
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
});
