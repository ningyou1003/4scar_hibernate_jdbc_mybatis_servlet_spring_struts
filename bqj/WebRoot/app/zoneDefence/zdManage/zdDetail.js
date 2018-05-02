var vm = avalon.define({
	$id : "zdDetailCtrl",
	data : {
		keyid : "",
		title : "",
		content : ""
	},
});
var opType;
var status;
var zd_keyid;
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
				window.location = "zdList.html";
			}
		});
		$("#flowApproved").click(function() {
			flowApprove("zoneDefence", vm.data.keyid);
			window.location = "../../flow/flowManage/flowList.html?status=0";
		});
		$("#report").click(function() {
			flowSend("zoneDefence", vm.data.keyid);
		});
		/*$("#flowBack").click(function() {
			flowBack("zoneDefence", vm.data.keyid);
		});*/
	},
	initData : function() {
		var request = Public.urlRequest();
		opType = request.queryString["opType"];
		status  = request.queryString["status"];
		zd_keyid = request.queryString["keyid"];
		if(opType==1&& status!=1){
				document.getElementById("flowApproved").style.display = "";
				var regionid=$.cookie("regionCode");
				if (regionid!=null && regionid=='450000000000') {
					$("#report").css("display","none");
				}else {
					$("#report").css("display","");
				}
//				document.getElementById("flowBack").style.display = "";
		}
		if (zd_keyid!= null && zd_keyid.length > 0) {
			vm.data.keyid = zd_keyid;
			Public.ajaxPost(Public.rootPath() + "/zoneDefence/editData", "keyid="
					+ vm.data.keyid, function(json) {
				if (json.status == 001) {
					vm.data=json.data;
					$("#title").append(vm.data.title);
					$("#content").append(vm.data.content);
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
	infoAttList("z_zonedefence", vm.data.keyid);//获取附件列表
});
