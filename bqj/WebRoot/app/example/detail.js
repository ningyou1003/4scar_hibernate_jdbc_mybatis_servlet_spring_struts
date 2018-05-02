var vm = avalon.define({
	$id : "detailCtrl",
	data : {
		keyid : "",
		title : "",
		type : "",
		content : ""
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
			window.location = "activityList.html?type="+vm.typeid;
		});
		$("#flowApproved").click(function() {
			flowApprove("xxx", id);
		});
		$("#flowSend").click(function() {
			flowSend("xxx", id);
		});
		$("#flowBack").click(function() {
			flowBack("xxx", id);
		});
	},
	initData : function() {
		var request = Public.urlRequest();
		var opType = request.queryString["opType"];
		var status  = request.queryString["status"];
		var activity_keyid = request.queryString["keyid"];
		if(opType==1&& status!=1){
				document.getElementById("flowApproved").style.display = "";
				document.getElementById("flowSend").style.display = "";
				document.getElementById("flowBack").style.display = "";
		}
		if (activity_keyid != null && activity_keyid.length > 0) {
			vm.data.keyid  =activity_keyid;
			Public.ajaxPost(Public.rootPath() + "/activity/editData", "keyid="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data.act;
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
	infoAttList("activity", vm.data.keyid);//获取附件列表
});
