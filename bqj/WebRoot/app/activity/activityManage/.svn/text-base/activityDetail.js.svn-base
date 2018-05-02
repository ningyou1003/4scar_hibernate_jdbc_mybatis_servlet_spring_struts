var vm = avalon.define({
	$id : "activityDetailCtrl",
	data : {
		keyid : "",
		title : "",
		type : "",
		content : ""
	}
});
var opType;
var status;
var act_keyid;
THISPAGE = {
	init : function() {
		this.initDom();
		this.initEvent();
		this.initData();
	},
	initEvent : function() {
		$("#btback").click(function() {
			if(opType==1){//返回待办列表
				window.location = "../../flow/flowManage/flowList.html?status=0";
			} else if(opType==2){//返回已办列表
				window.location = "../../flow/flowManage/flowList.html?status=1";
			} else {
				window.location = "activityList.html?type="+vm.typeid;
			}
		});
		$("#flowApproved").click(function() {
			flowApprove("activity", vm.data.keyid);
		});
		$("#report").click(function() {
			flowSend("activity", vm.data.keyid);
		});
/*		$("#flowBack").click(function() {
			flowBack("xxx", id);
		});*/
	},
	initData : function() {
		var request = Public.urlRequest();
		opType = request.queryString["opType"];
		status  = request.queryString["status"];
		activity_keyid = request.queryString["keyid"];
		if(opType==1&& status!=1){
				document.getElementById("flowApproved").style.display = "";
				document.getElementById("report").style.display = "";
//				document.getElementById("flowBack").style.display = "";
		}
		if (activity_keyid != null && activity_keyid.length > 0) {
			vm.data.keyid  =activity_keyid;
			Public.ajaxPost(Public.rootPath() + "/activity/editData", "keyid="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data.act;
					vm.typeid = vm.data.type;
					$("#title").html(vm.data.title);
					$("#content").html(vm.data.content);
				} else {
					top.openeasydialog("error",json.msg);
				}
			});
		} 
	},
	initDom : function() {
	}
};

$(document).ready(function(e) {
	THISPAGE.init();
	infoAttList("activity", vm.data.keyid);
});
